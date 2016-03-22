package com.cuelogic.myapplication.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.cuelogic.myapplication.R;
import com.cuelogic.myapplication.interfaces.MyApplicationAPIs;
import com.cuelogic.myapplication.models.inputmodels.LoginUserInputData;
import com.cuelogic.myapplication.models.outputmodels.BaseOutputData;
import com.cuelogic.myapplication.models.outputmodels.LoginUserOutputData;
import com.cuelogic.myapplication.network.jsonparsers.NetworkErrorJsonParser;
import com.cuelogic.myapplication.network.networkmanager.ServiceManager;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginScreenFragment extends BaseFragment {

    private View rootView;
    private ProgressDialog progressDialog;

    private ImageView selectedImageView;
    private LinearLayout horizontalLinearLayout;

    private MyApplicationAPIs myApplicationAPIs;

    private DisplayImageOptions options;

    public LoginScreenFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myApplicationAPIs = ServiceManager.getInstance();
        initImageLoader(getActivity());
        initDisplayOptions();
        rootView = inflater.inflate(R.layout.fragment_login, container, false);

        Button loginButton = (Button) rootView.findViewById(R.id.login_button_id);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Logging in...");
                progressDialog.show();

                LoginUserInputData loginUserInputData = new LoginUserInputData();
                loginUserInputData.setUserName("Virag");
                loginUserInputData.setPassword("*******");
                myApplicationAPIs.loginUser(LoginScreenFragment.this, loginUserInputData);
            }
        });

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        LoginUserInputData loginUserInputData = new LoginUserInputData();
        loginUserInputData.setUserName("Virag");
        loginUserInputData.setPassword("*******");
        myApplicationAPIs.loginUser(LoginScreenFragment.this, new LoginUserInputData());

        horizontalLinearLayout = (LinearLayout) rootView.findViewById(R.id.horizontal_linear_layout_id);

        selectedImageView = (ImageView) rootView.findViewById(R.id.selected_image_view_id);

        return rootView;
    }

    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    public void onSuccessResponseReceived(BaseOutputData baseOutputData) {
        super.onSuccessResponseReceived(baseOutputData);
        progressDialog.dismiss();
        if (baseOutputData instanceof LoginUserOutputData) {
            LoginUserOutputData loginUserOutputData = (LoginUserOutputData) baseOutputData;
            final ArrayList<JSONArray> jsonArrays = loginUserOutputData.getJsonArrays();
            for(int i = 0; i < jsonArrays.size(); i++) {
                HorizontalScrollView horizontalScrollView = new HorizontalScrollView(getActivity());
                horizontalScrollView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

                ScrollView scrollView = new ScrollView(getActivity());
                scrollView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));

                final LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                for (int j = 0; j < jsonArrays.get(i).length(); j++) {
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(250, 250));
                    String path = null;
                    try {
                        path = (String) jsonArrays.get(i).getJSONObject(j).get("imgURL");
                    } catch (Exception exception) {

                    }

                    ImageLoader.getInstance().displayImage("http://192.168.10.104/"+path, imageView, options);

                    imageView.setId(j);
                    linearLayout.addView(imageView);
                    linearLayout.setId(i);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String imagePath = null;
                            try {
                                imagePath = (String) jsonArrays.get(linearLayout.getId()).getJSONObject(v.getId()).get("imgURL");
                            } catch (Exception exception) {

                            }
                            ImageLoader.getInstance().displayImage("http://192.168.10.104/" + imagePath, selectedImageView, options);

                        }
                    });

                }
                scrollView.addView(linearLayout);
                horizontalScrollView.addView(scrollView);
                horizontalLinearLayout.addView(horizontalScrollView);
            }
        }
    }

    @Override
    public void onFaliureResponseReceived(NetworkErrorJsonParser networkErrorJsonParser) {
        super.onFaliureResponseReceived(networkErrorJsonParser);
        progressDialog.dismiss();
    }

    private void initDisplayOptions() {
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new RoundedBitmapDisplayer(20))
                .build();
    }

    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }
}
