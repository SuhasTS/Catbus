package test.blowhorn.com.test;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parsing extends AppCompatActivity {
    ProgressDialog pDialog;
    ArrayList<custom> list=new ArrayList<custom>();
    RecyclerView listDisplay;
    LinearLayoutManager layoutManager;
    listAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parsing);
        // Tag used to cancel the request
        String tag_json_obj = "json_obj_req";
        listDisplay=(RecyclerView)findViewById(R.id.recyclerList) ;
        layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listDisplay.setLayoutManager(layoutManager);
        adapter=new listAdapter();
        listDisplay.setAdapter(adapter);
        String url = "https://blowhorntest.appspot.com/user/test/orders";

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray json = null;
                        pDialog.hide();
                        Log.v("test123", response.toString());
                        try {
                            json=response.getJSONArray("current");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        for(int i=0;i<json.length();i++)
                        {
                            try
                            {
                                JSONObject obj=json.getJSONObject(i);
                                custom tempObj=new custom();
                                tempObj.status=obj.getString("status");
                                tempObj.orderId=obj.getString("order_id");
                                Log.v("test123","Status:"+tempObj.status+"\tOrderId:"+tempObj.orderId);
                                list.add(tempObj);
                                adapter.notifyDataSetChanged();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                // hide the progress dialog
                Log.v("test123","Error");
                pDialog.hide();
            }
        });

// Adding request to request queue
        Appcontroller.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
    public class listAdapter extends RecyclerView.Adapter<listAdapter.listHolder>
    {
        @Override
        public listHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.listelement,parent,false);
            return new listHolder(itemView);
        }

        @Override
        public void onBindViewHolder(listHolder holder, int position)
        {
            holder.status.setText("Status: "+list.get(position).status);
            holder.orderId.setText("OrderId: "+list.get(position).orderId);

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class listHolder extends RecyclerView.ViewHolder {
            TextView orderId,status;
            public listHolder(View itemView) {
                super(itemView);
                orderId=(TextView)itemView.findViewById(R.id.orderId);
                status=(TextView)itemView.findViewById(R.id.status);
            }
        }


    }


}
class  custom
{
    String status;
    String orderId;
}
