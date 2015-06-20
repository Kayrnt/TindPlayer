package fr.kayrnt.tindplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.List;

import fr.kayrnt.tindplayer.model.FacebookAccount;

public class AccountsAdapter extends BaseAdapter implements SpinnerAdapter {

    private Context context;
    public List<FacebookAccount> items;

    public AccountsAdapter(Context context, List<FacebookAccount> list) {
        super();
        items = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final FacebookAccount facebookAccount = (FacebookAccount) getItem(position);
        if (view == null) {
            LayoutInflater localLayoutInflater = (LayoutInflater) context.getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            view = localLayoutInflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        TextView nameTextView = ((TextView) view.findViewById(android.R.id.text1));
        nameTextView.setText(facebookAccount.name);
        view.setTag(facebookAccount);

        return view;

    }
}