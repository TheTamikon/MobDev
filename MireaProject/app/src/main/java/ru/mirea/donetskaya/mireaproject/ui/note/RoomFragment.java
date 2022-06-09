package ru.mirea.donetskaya.mireaproject.ui.note;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Database;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.donetskaya.mireaproject.databinding.FragmentRoomBinding;
import ru.mirea.donetskaya.mireaproject.R;



public class RoomFragment extends Fragment {

    private DataBase db;
    private FragmentRoomBinding binding;
    private ListView lView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = Room.databaseBuilder(
                getContext(),
                DataBase.class,
                "db"
        )
                .allowMainThreadQueries()
                .build();
        data = new ArrayList<>();
        adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                data
        );

        List<Notes> backup = db.notesDao().getAll();
        if (!backup.isEmpty()){
            for (Notes note : backup){
                data.add(note.toDo + " " + note.when);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRoomBinding.inflate(
                inflater,
                container,
                false
        );
        View root = binding.getRoot();
        lView = (ListView) root.findViewById(R.id.listView);
        lView.setAdapter(adapter);
        return root;
    }

    public void on_btnSubmitClick(View v, String toDoTxt, String whenDoTxt) {
        Notes note = new Notes();
        note.toDo = toDoTxt;
        note.when = whenDoTxt;

        RoomDao nd = db.notesDao();
        nd.insert(note);

        data.add(toDoTxt + " " + whenDoTxt);
        adapter.notifyDataSetChanged();
    }

    public void on_btnRemoveClick(View v) {
        db.notesDao().deleteAll();
        data.clear();
        adapter.notifyDataSetChanged();
    }
}
