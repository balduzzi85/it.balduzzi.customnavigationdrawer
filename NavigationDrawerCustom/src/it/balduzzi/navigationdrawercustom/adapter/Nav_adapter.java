package it.balduzzi.navigationdrawercustom.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import it.balduzzi.navigationdrawercustom.R;
import it.balduzzi.navigationdrawercustom.interfaces.DrawerCallbacks;
import it.balduzzi.navigationdrawercustom.utils.NavigationItem;

import java.util.List;


/**
 * Created by antonio on 04/02/15.
 */
public class Nav_adapter extends RecyclerView.Adapter<Nav_adapter.ViewHolder> {

    private List<NavigationItem> mData;
    private DrawerCallbacks mDrawerCallbacks;
    private int mSelectedPosition;
    private int mTouchedPosition = -1;

    private static final int TYPE_HEADER = 0;  // Declaring Variable to Understand which View is being worked on
    // IF the view under inflation and population is header or Item
    private static final int TYPE_ITEM = 1;

    private String name;        //String Resource for header View Name
    private int profile;        //int Resource for header view profile picture
    private String email;

    public Nav_adapter(List<NavigationItem> data) {
        mData = data;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        int Holderid;

        TextView textView;
        ImageView profile;
        TextView Name;
        TextView email;

        public ViewHolder(View itemView, int ViewType) {
            super(itemView);

            // Here we set the appropriate view in accordance with the the view type as passed when the holder object is created

            if (ViewType == TYPE_ITEM) {

                textView = (TextView) itemView.findViewById(R.id.item_name);

                Holderid = 1;

            } else {
                Name = (TextView) itemView.findViewById(R.id.name);         // Creating Text View object from header.xml for name
                email = (TextView) itemView.findViewById(R.id.email);       // Creating Text View object from header.xml for email
                profile = (ImageView) itemView.findViewById(R.id.circleView);// Creating Image view object from header.xml for profile pic
                Holderid = 0;


            }

        }
    }

    public void setNavigationDrawerCallbacks(DrawerCallbacks drawerCallbacks) {
        mDrawerCallbacks = drawerCallbacks;
    }

    @Override
    public Nav_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_row, parent, false);

            ViewHolder vhItem = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view

            return vhItem; // Returning the created object


        }
        else if (viewType == TYPE_HEADER) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header,parent,false); //Inflating the layout

            ViewHolder vhHeader = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view

            return vhHeader; //returning the object created


        }


        return null;
    }
    @Override
    public void onBindViewHolder(Nav_adapter.ViewHolder holder, final int i) {



        if(holder.Holderid ==1) {

            holder.textView.setText(mData.get(i).getText());
            holder.textView.setCompoundDrawablesWithIntrinsicBounds(mData.get(i).getDrawable(), null, null, null);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                                                       @Override
                                                       public void onClick(View v) {

                                                           if (mDrawerCallbacks != null)
                                                               mDrawerCallbacks.onNavigationDrawerItemSelected(i);
                                                       }
                                                   }
            );


            if (mSelectedPosition == i || mTouchedPosition == i) {
                holder.itemView.setBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.selection));
            } else {
                holder.itemView.setBackgroundColor(Color.TRANSPARENT);
            }




        }
        else{

            holder.profile.setImageResource(profile);           // Similarly we set the resources for header view
            holder.Name.setText("Antonio Balduzzi");
            holder.email.setText("balduzzi@gmail.com");
        }
    }


    public void selectPosition(int position) {
        int lastPosition = mSelectedPosition;
        mSelectedPosition = position;
        notifyItemChanged(lastPosition);
        notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

  

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }



}