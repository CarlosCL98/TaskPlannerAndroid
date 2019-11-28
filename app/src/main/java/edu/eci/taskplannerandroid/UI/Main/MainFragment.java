package edu.eci.taskplannerandroid.UI.Main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.eci.taskplannerandroid.Network.Data.Task;
import edu.eci.taskplannerandroid.Network.RetrofitNetwork;
import edu.eci.taskplannerandroid.R;
import edu.eci.taskplannerandroid.Storage.Storage;
import edu.eci.taskplannerandroid.UI.Adapter.TaskAdapter;

public class MainFragment extends Fragment {

    private MainViewModel mainViewModel;
    private RecyclerView recyclerView;
    private RetrofitNetwork retrofitNetwork;
    private Storage storage;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        configureRecyclerView();
        retrofitNetwork.getTaskPlannerRoomDatabase().taskDao().getTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                recyclerView.setAdapter(new TaskAdapter(tasks));
            }
        });

    }

    private void configureRecyclerView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        storage = new Storage(context);
        retrofitNetwork = new RetrofitNetwork(storage.getToken(), context);
        retrofitNetwork.getTasks();
    }
}
