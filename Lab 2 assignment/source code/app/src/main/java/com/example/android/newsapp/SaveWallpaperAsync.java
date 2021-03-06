package com.example.android.newsapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by ata6fd on 17-11-2017.
 */

public class SaveWallpaperAsync extends AsyncTask<String, String, String> {
    private Context context;


    public SaveWallpaperAsync(Context context) {

        this.context = context;
    }



    @Override
    protected String doInBackground(String... args) {


        InputStream is = null;


        try
        {
            URL url = new URL(args[0]);
            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1!=(n=in.read(buf)))
            {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();

            File dirfl =  new File(Environment.getExternalStorageDirectory().getPath()+"/Guardian news");

            if (!dirfl.exists())
                dirfl.mkdir();

            String filename = dirfl.getAbsolutePath()+"/"+args[0].substring(args[0].lastIndexOf("/")+1,args[0].lastIndexOf("?"));

            FileOutputStream fos = new FileOutputStream(filename);
            fos.write(response);
            fos.close();
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context,"Downloading Image...",Toast.LENGTH_SHORT).show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String args) {



Toast.makeText(context,"Image Downloaded",Toast.LENGTH_SHORT).show();
        }
    }


