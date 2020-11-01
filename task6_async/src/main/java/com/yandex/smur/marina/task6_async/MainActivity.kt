package com.yandex.smur.marina.task6_async

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yandex.smur.marina.task6_async.async.CompletableFutureThreadPoolExecutor
import com.yandex.smur.marina.task6_async.async.RepositoryInterface
import com.yandex.smur.marina.task6_async.async.RxJava
import com.yandex.smur.marina.task6_async.async.ThreadPoolExecutorHandler
import com.yandex.smur.marina.task6_async.database.DBHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class MainActivity : AppCompatActivity() {

        private val THREADPOOLEXECUTER_HANDLER : String = "THREADPOOLEXECUTER_HANDLER"
        private val COMPLETABLEFUTURE_THREADPOOLEXECUTOR = "COMPLETABLEFUTURE_THREADPOOLEXECUTOR"
        private val RXJAVA = "RXJAVA"

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var mAdapter: ContactListAdapter
    private lateinit var dataBase: SQLiteDatabase
    private lateinit var contentValues: ContentValues
    private lateinit var dbHelper: DBHelper
    private var contacts: MutableList<Contact> = mutableListOf()
    private lateinit var repository : RepositoryInterface



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this);
        dataBase = dbHelper.writableDatabase;
        contentValues = ContentValues();

        persons.apply {
            viewManager = LinearLayoutManager(this@MainActivity)
            adapter = ContactListAdapter(contacts, object : ContactListAdapter.OnclickListener{
                override fun onItemClick(contact: Contact) {
                    val intent = Intent(this@MainActivity, EditContactActivity::class.java)
                    intent.putExtra("contact", contact)
                    startActivityForResult(intent, 3)
                }
            })
            mAdapter = adapter as ContactListAdapter
        }

        val str = intent.getStringExtra("async").toString()

        if (str.equals(THREADPOOLEXECUTER_HANDLER)) {
            repository = ThreadPoolExecutorHandler(contacts, dataBase, mAdapter)
        }

        contacts = repository.addContactsFromDataBase()

        buttonSetting.setOnClickListener{
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }


        emptyList()


        buttonAddPerson.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, com.yandex.smur.marina.task6_async.AddPersonActivity::class.java)
            startActivityForResult(intent, 2)
        })


        searchListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        dataBase.close()
        repository.closeThreads()
    }

    private fun searchListener() {
        editViewSearchEditFrame.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                mAdapter = (persons.adapter as ContactListAdapter?)!!
                if (mAdapter != null) {
                    filter(editable.toString())
                }
            }
        })
    }

    public fun filter(text: String) {
        val filterList: MutableList<Contact> = mutableListOf()
        for (item in contacts) {
            if (item.name.contains(text.toString())) {
                filterList.add(item)
            }
        }
        mAdapter.filterList(filterList)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                var contact = data!!.getSerializableExtra(Contact::class.java.simpleName) as Contact
                updateList(contact)
//                repositorySwitch()
                repository.addContactToDataBase(contact)
            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_CANCELED) {
                var contact = data!!.getSerializableExtra(Contact::class.java.simpleName) as Contact
                updateListAfterRemove(contact)
//                repositorySwitch()
                repository.deleteContactFromDataBase(contact)
            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                var contact = data!!.getSerializableExtra(Contact::class.java.simpleName) as Contact
                updateListBeforeChange(contact)
//                repositorySwitch()
                repository.editCountFromDataBase(contact)
            }
        }
        emptyList()
    }

    //Work with recyclerView (update)
    private fun updateList(contact: Serializable?) {
        mAdapter = (persons.adapter as ContactListAdapter?)!!
        if (contact != null) {
            mAdapter.addItem(contact as Contact)
        }
    }

    private fun updateListAfterRemove(contact: Serializable?) {
        mAdapter = (persons.adapter as ContactListAdapter?)!!
        if (contact != null) {
            mAdapter.deleteItem(contact as Contact)
        }
        mAdapter.notifyDataSetChanged()
    }

    private fun updateListBeforeChange(contact: Serializable?) {
        mAdapter = (persons.adapter as ContactListAdapter?)!!
        if (contact != null) {
            mAdapter.replaceItem(contact as Contact)
        }
        mAdapter.notifyDataSetChanged()
    }

    // RecyclerView or emptyView
    private fun emptyList() {
        if (mAdapter.itemCount == 0) {
            persons.visibility = View.GONE
            textViewEmptyView.visibility = View.VISIBLE
        } else {
            persons.visibility = View.VISIBLE
            textViewEmptyView.visibility = View.GONE
        }
    }
}
