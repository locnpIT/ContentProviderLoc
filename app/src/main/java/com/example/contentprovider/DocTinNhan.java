package com.example.contentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.contentprovider.adapter.AdapterTinNhan;
import com.example.contentprovider.model.TinNhan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DocTinNhan extends AppCompatActivity {

    private static final int REQUEST_CODE_ASK_PERMISSION = 1001;
    ListView lvDocTinNhan;
    ArrayList<TinNhan> dsTinNhan;
    AdapterTinNhan adapterTinNhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doc_tin_nhan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addControls();
        docTatCaTinNhan();
    }

    private void docTatCaTinNhan() {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        dsTinNhan.clear();
        while (cursor.moveToNext()){
            int indexPhoneNumber = cursor.getColumnIndex("address");
            int indexTimeStamp = cursor.getColumnIndex("date");
            int indexBody = cursor.getColumnIndex("body");

            String phoneNumber = cursor.getString(indexPhoneNumber);
            String timeStamp = cursor.getString(indexTimeStamp);
            String body = cursor.getString(indexBody);

            dsTinNhan.add(new TinNhan(phoneNumber, df.format(Long.parseLong(timeStamp)), body));
            adapterTinNhan.notifyDataSetChanged();
        }
    }

    private void addControls() {
        lvDocTinNhan = findViewById(R.id.lvDocTinNhan);
        dsTinNhan = new ArrayList<>();
        adapterTinNhan = new AdapterTinNhan(
                DocTinNhan.this, R.layout.item_tinnhan, dsTinNhan
        );
        lvDocTinNhan.setAdapter(adapterTinNhan);
    }
}