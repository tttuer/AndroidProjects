package com.example.jayyoungyang.management;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;

public class UserListAdapter extends BaseAdapter {
    private Context context;
    private List<User> userList;
    private Activity parentActivity;

    public UserListAdapter(Context context, List<User> userList, Activity parentActivity) {
        this.context = context;
        this.userList = userList;
        this.parentActivity = parentActivity;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.user, null);
        final TextView userID = (TextView) v.findViewById(R.id.userID);
        TextView userPassword = (TextView) v.findViewById(R.id.userPassword);
        TextView userName = (TextView) v.findViewById(R.id.userName);
        TextView userAge = (TextView) v.findViewById(R.id.userAge);

        userID.setText(userList.get(position).getUserID());
        userPassword.setText(userList.get(position).getUserPassword());
        userName.setText(userList.get(position).getUserName());
        userAge.setText(userList.get(position).getUserAge());

        v.setTag(userList.get(position).getUserID());

        Button deleteButton = (Button) v.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 결과에 대한 값을 받아들일수 있는 리스너 생성
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // 특정 웹사이트로부터 리스폰스 값이 전달되었을때 리스폰스 값을 받을 수 있도록 한다.
                            JSONObject jsonResponse = new JSONObject(response);
                            // 받아온 값이 success이면
                            boolean success = jsonResponse.getBoolean("success");
                            if(success) {
                                // 그 값을 지워주고
                                userList.remove(position);
                                // 값이 변경되었다는 것을 어댑터에게 알려준다.
                                notifyDataSetChanged();

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                // 딜리트리퀘스트 부분을 실행할 수 있도록 한다.
                DeleteRequest deleteRequest = new DeleteRequest(userID.getText().toString(), responseListener);
                RequestQueue queue = Volley.newRequestQueue(parentActivity);
                queue.add(deleteRequest);
            }
        });
        return v;
    }
}
