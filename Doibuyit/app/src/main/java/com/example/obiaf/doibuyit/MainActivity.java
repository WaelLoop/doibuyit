package com.example.obiaf.doibuyit;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Console;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import org.w3c.dom.Text;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    
    DatabaseReference databaseCustomers;
    int counter = 0;
    ArrayList<String> userList = new ArrayList<>();
    ArrayList<String> passList = new ArrayList<>();
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseCustomers = FirebaseDatabase.getInstance().getReference("Customers");
        Customers customer1 = new Customers("Affan", 12,"Affan69","sexy");
        databaseCustomers.child(counter + "").setValue(customer1);
        counter++;
        Customers customer2 = new Customers("Wael", 123,"wael12","sexy");
        databaseCustomers.child(counter + "").setValue(customer2);

    }

    @Override
    protected void onStart() {
        super.onStart();

        //all fields from activity_main.xml we will have to change
        TextView overviewBalance = (TextView) findViewById(R.id.overviewBalance);   //total amount
        TextView overviewMonthBudget = (TextView) findViewById(R.id.overviewMonthBudget);   //Monthly budget field
        RoundCornerProgressBar progress1 = (RoundCornerProgressBar) findViewById(R.id.weeklyProgress);  //weekly progress bar
        TextView amountLeftWk = (TextView) findViewById(R.id.amountLeftWk); //how much money left for week
        TextView monthlyProgressText = (TextView) findViewById(R.id.monthlyProgressText);   //percent of monthly budget left
        CircularProgressBar circularProgressBar = (CircularProgressBar)findViewById(R.id.monthlyProgress);  //monthly progress bar


        //all variables that will be used to show information
        String balance;     //total amount of money customer has
        String monthlyBudget;   //how much money customer wants to spend during the month
        String moneySpentMonth; //how much money spent this month
        String moneySpentWeek; //how much money spent this week
        String name;    //name of customer

        //TODO: initialize all the above fields

        //set total balance
        overviewBalance.setText(balance);

        //set monthly budget
        overviewMonthBudget.setText(monthlyBudget);

        //set weekly progress
        progress1.setProgressColor(Color.parseColor("#FFFFFF"));
        progress1.setProgressBackgroundColor(ContextCompat.getColor(this.context, R.color.moneyGreen));
        progress1.setMax(Float.parseFloat(monthlyBudget)/4);  //for a weekly budget we divide by 4
        progress1.setProgress(Float.parseFloat(moneySpentWeek);

        //set amount left text
        String rounded = String.format("%.2f",Double.parseDouble(monthlyBudget)/4);
        amountLeftWk.setText(Double.parseDouble(rounded) - Double.parseDouble(moneySpentWeek)+"$ left for this week.");

        //set text for monthly progress percentage
        monthlyProgressText.setText(""+(Double.parseDouble(monthlyBudget)/Double.parseDouble(moneySpentMonth)));
        int animationDuration = 2500; // 2500ms = 2,5s
        circularProgressBar.setProgressWithAnimation((Float.parseFloat(monthlyBudget)/Float.parseFloat(moneySpentMonth)), animationDuration); // Default duration = 1500ms
    }


}
