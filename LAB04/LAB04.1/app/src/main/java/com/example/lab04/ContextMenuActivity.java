package com.example.lab04;

import android.drm.DrmStore;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContextMenuActivity extends AppCompatActivity {
    ListView listView;
    public ActionMode mActionMode;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu);

         listView = findViewById(R.id.myListView);
        UserAccount tom = new UserAccount("Tom","admin");
        UserAccount jerry = new UserAccount("Jerry","user");
        UserAccount donald = new UserAccount("Donald","guest", false);

        UserAccount[] users = new UserAccount[]{tom,jerry, donald};

        ArrayAdapter<UserAccount> arrayAdapter = new
                ArrayAdapter<UserAccount>(this,android.R.layout.simple_dropdown_item_1line,users);
        listView.setAdapter(arrayAdapter);

        registerForContextMenu(listView);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mActionMode = startActionMode(mActionModeCallBack);
            }
        });

        //       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                PopupMenu popupMenu = new PopupMenu(getApplicationContext(),view);
//                popupMenu.getMenuInflater().inflate(R.menu.context_menu,popupMenu.getMenu());
//                popupMenu.show();
//           }
//       });
    }

     private ActionMode.Callback mActionModeCallBack = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(R.menu.context_menu,menu);
            actionMode.setTitle("Opened contextual menu");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.option_1:
                    Toast.makeText(getApplicationContext(),"Edit",Toast.LENGTH_SHORT).show();
                    actionMode.finish();
                    return true;
                case R.id.option_2:
                    Toast.makeText(getApplicationContext(),"Share",Toast.LENGTH_SHORT).show();
                    actionMode.finish();
                    return true;
                case R.id.option_3:
                    Toast.makeText(getApplicationContext(),"Delete",Toast.LENGTH_SHORT).show();
                    actionMode.finish();
                    return true;
                default:
                    return false;
            }
        }
        @Override
        public void onDestroyActionMode(ActionMode actionMode) {

        }
    };

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.floating_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch(item.getItemId()){
            case R.id.cnt_mnu_edit:
                Toast.makeText(this, "Edit : "  , Toast.LENGTH_SHORT).show();
                break;
            case R.id.cnt_mnu_delete:
                Toast.makeText(this, "Delete : "   , Toast.LENGTH_SHORT).show();
                break;
            case R.id.cnt_mnu_share:
                Toast.makeText(this, "Share : "  , Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }
}
