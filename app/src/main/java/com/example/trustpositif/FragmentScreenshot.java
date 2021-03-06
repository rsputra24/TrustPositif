package com.example.trustpositif;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.nileshp.multiphotopicker.photopicker.activity.PickImageActivity;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class FragmentScreenshot extends Fragment {
    View view;
    private ImageView imageView[];
    private static ArrayList<String> pathList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_screenshot, container, false);
        imageView = new ImageView[3];
        imageView[0] = (ImageView) view.findViewById(R.id.SCView1);
        imageView[1] = (ImageView) view.findViewById(R.id.SCView2);
        imageView[2] = (ImageView) view.findViewById(R.id.SCView3);
        Button pickImageButton = (Button) view.findViewById(R.id.pick_image_button);
        pickImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        return view;
    }

    private void openGallery() {
        Intent mIntent = new Intent(FragmentScreenshot.this.getActivity(), PickImageActivity.class);
        mIntent.putExtra(PickImageActivity.KEY_LIMIT_MAX_IMAGE, 3);
        mIntent.putExtra(PickImageActivity.KEY_LIMIT_MIN_IMAGE, 1);
        startActivityForResult(mIntent, PickImageActivity.PICKER_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == PickImageActivity.PICKER_REQUEST_CODE) {
            pathList = intent.getExtras().getStringArrayList(PickImageActivity.KEY_DATA_RESULT);
            Uri imageUri[] = new Uri[3];
            if (pathList != null && !pathList.isEmpty()) {
                for (int i = 0; i < pathList.size(); i++) {
                    if (pathList.get(i) != null) {
                        imageUri[i] = Uri.parse(pathList.get(i));
                        imageView[i].setImageURI(imageUri[i]);
                    }
                }
            }
        }
    }

    public static ArrayList<String> getImage(){
        if(!(pathList==null)){
            return pathList;
        }else {
            ArrayList<String> returnPathlist;
            returnPathlist = new ArrayList<>();
            return returnPathlist;
        }

    }
}
