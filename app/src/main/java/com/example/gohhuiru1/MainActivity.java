package com.example.gohhuiru1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ViewGroup layoutGroup;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference m1;
    //String seats = "_UUUAAARRRR_/_________________/UU__AARRR__RR/UU__UAAAA__AA/AA__AAAAA__AA/AA__AAURR__AA/UU__UURRR__AA/AA__AARAA__UU/AA__AAUUU__RR/AA__UUURR__RR/_________________/UU_AAAUUUU_RR/RR_AAAAAAA_AA/_________________/";
    // new
    String seats;
    LinearLayout layoutSeat;
    LinearLayout layout;

    List<TextView> seatViewList = new ArrayList<>();
    int seatSize = 70;
    int seatGaping = 10;
    int count = 0;
    int countSelect;

    int STATUS_AVAILABLE = 1;
    int STATUS_BOOKED = 2;
    int STATUS_RESERVED = 3;
    String selectedIds = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m1 = firebaseDatabase.getReference("movie").child("m1");
        layoutGroup = findViewById(R.id.layoutSeat);

        //seats = "/" + seats;

        layoutSeat = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutSeat.setOrientation(LinearLayout.VERTICAL);
        layoutSeat.setLayoutParams(params);
        layoutSeat.setPadding(8 * seatGaping, 8 * seatGaping, 8 * seatGaping, 8 * seatGaping);
        layoutGroup.addView(layoutSeat);

        layout = null;


/*
        for (int index = 0; index < seats.length(); index++) {

            if (seats.charAt(index) == '/') {
                layout = new LinearLayout(this);
                layout.setOrientation(LinearLayout.HORIZONTAL);
                layoutSeat.addView(layout);
            } else if (seats.charAt(index) == 'U') {
                count++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.ic_seats_booked);
                view.setTextColor(Color.WHITE);
                view.setBackgroundColor(Color.RED);
                view.setTag(STATUS_BOOKED);
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
            } else if (seats.charAt(index) == 'R') {
                count++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.ic_seats_reserved);
                view.setBackgroundColor(Color.GRAY);
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                view.setTextColor(Color.BLACK);
                view.setTag(STATUS_RESERVED);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
            } else if (seats.charAt(index) == 'A') {
                count++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.ic_seats_book);
                view.setBackgroundColor(Color.BLUE);
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                view.setTextColor(Color.WHITE);
                view.setTag(STATUS_AVAILABLE);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
                seats = seats.substring(0,index)+'U'+seats.substring(index+1);
            } else if (seats.charAt(index) == '_') {
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setBackgroundColor(Color.TRANSPARENT);
                view.setText("");
                layout.addView(view);
            }
        }

 */
        // take firebase
        m1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                seats = dataSnapshot.child("movie_seat").getValue(String.class);
                seats = "/" + seats;
                for (int index = 0; index < seats.length(); index++) {

                    if (seats.charAt(index) == '/') {
                        layout = new LinearLayout(MainActivity.this);
                        layout.setOrientation(LinearLayout.HORIZONTAL);
                        layoutSeat.addView(layout);
                    } else if (seats.charAt(index) == 'U') {
                        count++;
                        TextView view = new TextView(MainActivity.this);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                        layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                        view.setLayoutParams(layoutParams);
                        view.setPadding(0, 0, 0, 2 * seatGaping);
                        view.setId(count);
                        view.setGravity(Gravity.CENTER);
                        view.setBackgroundResource(R.drawable.ic_seats_booked);
                        view.setTextColor(Color.WHITE);
                        view.setBackgroundColor(Color.RED);
                        view.setTag(STATUS_BOOKED);
                        view.setText(count + "");
                        view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                        layout.addView(view);
                        seatViewList.add(view);
                        view.setOnClickListener(MainActivity.this);
                    } else if (seats.charAt(index) == 'R') {
                        count++;
                        TextView view = new TextView(MainActivity.this);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                        layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                        view.setLayoutParams(layoutParams);
                        view.setPadding(0, 0, 0, 2 * seatGaping);
                        view.setId(count);
                        view.setGravity(Gravity.CENTER);
                        view.setBackgroundResource(R.drawable.ic_seats_reserved);
                        view.setBackgroundColor(Color.GRAY);
                        view.setText(count + "");
                        view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                        view.setTextColor(Color.BLACK);
                        view.setTag(STATUS_RESERVED);
                        layout.addView(view);
                        seatViewList.add(view);
                        view.setOnClickListener(MainActivity.this);
                    } else if (seats.charAt(index) == 'A') {
                        count++;
                        TextView view = new TextView(MainActivity.this);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                        layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                        view.setLayoutParams(layoutParams);
                        view.setPadding(0, 0, 0, 2 * seatGaping);
                        view.setId(count);
                        view.setGravity(Gravity.CENTER);
                        view.setBackgroundResource(R.drawable.ic_seats_book);
                        view.setBackgroundColor(Color.BLUE);
                        view.setText(count + "");
                        view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                        view.setTextColor(Color.WHITE);
                        view.setTag(STATUS_AVAILABLE);
                        layout.addView(view);
                        seatViewList.add(view);
                        view.setOnClickListener(MainActivity.this);
                        //seats = seats.substring(0, index) + 'U' + seats.substring(index + 1);
                    } else if (seats.charAt(index) == '_') {
                        TextView view = new TextView(MainActivity.this);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                        layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                        view.setLayoutParams(layoutParams);
                        view.setBackgroundColor(Color.TRANSPARENT);
                        view.setText("");
                        layout.addView(view);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        if ((int) view.getTag() == STATUS_AVAILABLE) {
            if (selectedIds.contains(view.getId() + ",")) {
                selectedIds = selectedIds.replace(+view.getId() + ",", "");
                view.setBackgroundResource(R.drawable.ic_seats_book);
                view.setBackgroundColor(Color.BLUE);
            } else {
                selectedIds = selectedIds + view.getId() + ",";
                view.setBackgroundResource(R.drawable.ic_seats_selected);
                view.setBackgroundColor(Color.GREEN);
                Toast.makeText(this, "Seat " + view.getId() + " is Selected", Toast.LENGTH_SHORT).show();
                countSelect = view.getId();
                //int n = view.getId();
                //String n2 = seats = seats.substring(0, n-1) + 'C' + seats.substring(n);
                //Toast.makeText(this, seats, Toast.LENGTH_SHORT).show();
            }
        } else if ((int) view.getTag() == STATUS_BOOKED) {
            Toast.makeText(this, "Seat " + view.getId() + " is Booked", Toast.LENGTH_SHORT).show();
        } else if ((int) view.getTag() == STATUS_RESERVED) {
            Toast.makeText(this, "Seat " + view.getId() + " is Reserved", Toast.LENGTH_SHORT).show();
        }
    }

    public void confirm(View view) {
        /*
        //Toast.makeText(this, "You already select seat " + countSelect, Toast.LENGTH_SHORT).show();
        String newSeat = "";
        for (int index = 0; index < count; index++) {
            String sID = String.valueOf(index);
            int ID = getResources().getIdentifier(sID, "id", getPackageName());
            TextView t = (TextView)findViewById(ID);
            String s = t.getText().toString();
            Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
            //String z = seatViewList.get(index).getText().toString();
            //Toast.makeText(this,z,Toast.LENGTH_SHORT).show();
            //layoutSeat.getChildAt(0).getLayoutParams().toString();
            if(seats.charAt(index) == '/'){
                newSeat += "/";
            }
        }
        int a = layoutGroup.getChildCount();
        int b = layoutSeat.getChildCount();
        int c = layout.getChildCount();
        int d = seatViewList.size();


        Toast.makeText(this, a+"\n"+b+"\n"+c+"\n"+d , Toast.LENGTH_SHORT).show();*/
        //Toast.makeText(this,z,Toast.LENGTH_SHORT).show();
    }
}