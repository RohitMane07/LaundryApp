package com.xbiztechventures.lapp.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xbiztechventures.lapp.R;


/**
 * Created by Rohit on 21/01/2017.
 */
public class SwipeAdapter extends PagerAdapter {

    int imageResources[]={R.mipmap.profile1,R.mipmap.profile2};
    String users[]={"Kunal Parikh","Rohit Mane"};
    String reviews[]={"I  had Great experiance with this laundry","I  had Great experiance with this laundry"};
    Context ctx;
    LayoutInflater layoutInflater;
    public SwipeAdapter(Context ctx){
        this.ctx=ctx;
    }
    @Override
    public int getCount() {
        return imageResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(LinearLayout)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view =layoutInflater.inflate(R.layout.review_layout,container,false);
        ImageView img1=(ImageView) item_view.findViewById(R.id.circle);
        TextView txt=(TextView)item_view.findViewById(R.id.textview);
        TextView txtusername=(TextView)item_view.findViewById(R.id.username);
        ImageView forward=(ImageView)item_view.findViewById(R.id.forward);
        ImageView backward =(ImageView)item_view.findViewById(R.id.backward) ;
        img1.setImageResource(imageResources[position]);
        txt.setText(reviews[position]);
        txtusername.setText(users[position]);
        container.addView(item_view);
        if(position==0)
            backward.setVisibility(View.INVISIBLE);
        if(position==users.length-1)
            forward.setVisibility(View.INVISIBLE);
       return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
