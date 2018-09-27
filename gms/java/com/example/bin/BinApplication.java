package com.example.bin;

import android.app.Application;
import com.example.lib.MyApplication;
import com.example.bin.R;

public class BinApplication extends Application implements MyApplication {

  @Override
  public int getContentView() {
    return R.layout.main_with_ads;
  }
}

