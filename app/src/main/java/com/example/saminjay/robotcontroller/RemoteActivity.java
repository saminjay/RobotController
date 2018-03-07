package com.example.saminjay.robotcontroller;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class RemoteActivity extends AppCompatActivity {

    //SPP UUID. Look for it
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    int fl = 0;
    String address = null;
    BluetoothAdapter myBluetooth = null;
    BluetoothDevice bluetoothDevice;
    BluetoothSocket btSocket = null;
    private ProgressDialog progress;
    private boolean isBtConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);

        address = getIntent().getStringExtra(ListActivity.EXTRA_ADDRESS); //receive the address of the bluetooth device

        new ConnectBT().execute(); //Call the class to connect

    }

    //for toast
    private void msg(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    public void Stop(View v) {

        try {
            btSocket.getOutputStream().write("0".getBytes());
        } catch (IOException e) {
            msg("ERROR");
        }

    }

    public void Up(View v) {

        try {
            btSocket.getOutputStream().write("u".getBytes());
        } catch (IOException e) {
            msg("ERROR");
        }

    }

    public void Down(View v) {

        try {
            btSocket.getOutputStream().write("d".getBytes());
        } catch (IOException e) {
            msg("ERROR");
        }

    }

    public void Left(View v) {

        try {
            btSocket.getOutputStream().write("l".getBytes());
        } catch (IOException e) {
            msg("ERROR");
        }

    }

    public void Right(View v) {

        try {
            btSocket.getOutputStream().write("r".getBytes());
        } catch (IOException e) {
            msg("ERROR");
        }

    }

    public void HeadLeft(View v) {

        try {
            btSocket.getOutputStream().write("1".getBytes());
        } catch (IOException e) {
            msg("ERROR");
        }

    }

    public void HeadRight(View v) {

        try {
            btSocket.getOutputStream().write("2".getBytes());
        } catch (IOException e) {
            msg("ERROR");
        }

    }

    public void LeftUp(View v) {

        try {
            btSocket.getOutputStream().write("3".getBytes());
        } catch (IOException e) {
            msg("ERROR");
        }

    }

    public void RightUp(View v) {

        try {
            btSocket.getOutputStream().write("4".getBytes());
        } catch (IOException e) {
            msg("ERROR");
        }

    }

    public void RightDown(View v) {

        try {
            btSocket.getOutputStream().write("5".getBytes());
        } catch (IOException e) {
            msg("ERROR");
        }

    }

    public void LeftDown(View v) {

        try {
            btSocket.getOutputStream().write("6".getBytes());
        } catch (IOException e) {
            msg("ERROR");
        }

    }

    public void HeadCentre(View v) {

        try {
            btSocket.getOutputStream().write("7".getBytes());
        } catch (IOException e) {
            msg("ERROR");
        }
    }

    public void auto(View v) {

        if (fl == 0) {


            if (btSocket != null) {
                try {
                    btSocket.getOutputStream().write("a".getBytes());

                    TextView textView = (TextView) findViewById(R.id.auto);
                    textView.setText("Stop Autorun");

                    fl++;

                } catch (IOException e) {
                    msg("Error");
                }

            }
        } else {

            if (btSocket != null) {
                try {
                    btSocket.getOutputStream().write("0".getBytes());

                    TextView textView = findViewById(R.id.auto);
                    textView.setText("Autorun");

                    fl = 0;

                } catch (IOException e) {
                    msg("Error");
                }

            }

        }

    }

    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(RemoteActivity.this, "Connecting...", "Please Wait!!!");  //show a progress dialog
        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try {
                if (btSocket == null || !isBtConnected) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    bluetoothDevice = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            } catch (IOException e) {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess) {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                finish();
            } else {
                msg("Connected.");
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }

}
