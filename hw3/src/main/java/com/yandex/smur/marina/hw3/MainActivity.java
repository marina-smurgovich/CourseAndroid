package com.yandex.smur.marina.hw3;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    private ImageButton buttonAddContact;
    private EditText searchName;
    private RecyclerView persons;
    private TextView emptyView;
    ContactListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emptyView = findViewById(R.id.emptyView);
        persons = findViewById(R.id.persons);

        persons.setAdapter(new ContactListAdapter(new ContactListAdapter.OnclickListener() {
            @Override
            public void onItemClick(Contact contact) {
                Intent intent = new Intent(MainActivity.this, EditContactActivity.class);
                intent.putExtra("name", contact.getName());
                intent.putExtra("telOrEmail", contact.getTelOrEmail());
                intent.putExtra("image", contact.getImage());
                startActivityForResult(intent, 3);
            }
        }));

        persons.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        buttonAddContact = findViewById(R.id.addPerson);
        buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddPersonActivity.class);
                startActivityForResult(intent, 2);
            }
        });

        searchName = findViewById(R.id.search_edit_frame);
        searchName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                adapter = (ContactListAdapter) persons.getAdapter();
                if (adapter != null) {
                    adapter.filter(editable.toString());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == AddPersonActivity.RESULT_OK) {
                Contact contact = (Contact) data.getExtras().getSerializable(Contact.class.getSimpleName());
                updateList(contact);
            }
        }

        if (requestCode == 3) {
            if (resultCode == EditContactActivity.RESULT_CANCELED) {
                updateListAfterRemove();
            }
        }

        if (requestCode == 3) {
            if (resultCode == EditContactActivity.RESULT_OK) {
                Contact contact = (Contact) data.getExtras().getSerializable(Contact.class.getSimpleName());
                updateListBeforeChange(contact);
            }
        }

        emptyList();
    }

    private void updateListAfterRemove() {
        adapter = (ContactListAdapter) persons.getAdapter();
        if (adapter != null) {
            adapter.deleteItem();
        }
    }

    private void updateList(Contact contact) {
        adapter = (ContactListAdapter) persons.getAdapter();
        if (adapter != null) {
            adapter.addItem(contact);
        }
    }

    private void updateListBeforeChange(Contact contact) {
        adapter = (ContactListAdapter) persons.getAdapter();
        adapter.replaceItem(contact);
        adapter.notifyDataSetChanged();
    }

    private void emptyList() {
        adapter = (ContactListAdapter) persons.getAdapter();
        if (Objects.requireNonNull(adapter).getItemCount() == 0) {
            persons.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            persons.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }

    public static class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactItemViewHolder> {

        private List<Contact> contacts = new ArrayList<Contact>();
        private final OnclickListener listener;
        int position;

        public interface OnclickListener {
            void onItemClick(Contact contact);
        }

        public ContactListAdapter(@NonNull OnclickListener listener) {
            this.listener = listener;
        }

        @NonNull
        @Override
        public ContactItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new ContactItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ContactItemViewHolder holder, int position) {
            holder.bind(contacts.get(position), listener);
        }

        @Override
        public int getItemCount() {
            return contacts != null ? contacts.size() : 0;
        }

        public void addItem(Contact contact) {
            contacts.add(contact);
            notifyItemChanged(contacts.indexOf(contact));
        }

        public void deleteItem() {
            contacts.remove(position);
            notifyItemRemoved(position);
        }

        public void replaceItem(Contact contact) {
            contacts.set(position, contact);
        }

        public void filter(String text) {
            ArrayList<Contact> filterList = new ArrayList<>();

            for (Contact item : contacts) {
                if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                    filterList.add(item);
                }
            }
            filterList(filterList);
        }

        private void filterList(ArrayList<Contact> filterList) {
            contacts = filterList;
            notifyDataSetChanged();
        }

        private class ContactItemViewHolder extends RecyclerView.ViewHolder {
            private TextView item_name;
            private TextView item_phoneOrEmail;
            private ImageView imageView;

            public ContactItemViewHolder(@NonNull View itemView) {
                super(itemView);
                item_name = itemView.findViewById(R.id.item_name);
                item_phoneOrEmail = itemView.findViewById(R.id.item_phoneOrEmail);
                imageView = itemView.findViewById(R.id.image_item);
            }

            public void bind(final Contact contact, final OnclickListener listener) {
                item_name.setText(contact.getName());
                item_phoneOrEmail.setText(contact.getTelOrEmail());
                imageView.setImageResource(contact.getImage());

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (listener != null) {
                            position = getAdapterPosition();
                            listener.onItemClick(contact);
                        }
                    }
                });
            }
        }
    }
}

