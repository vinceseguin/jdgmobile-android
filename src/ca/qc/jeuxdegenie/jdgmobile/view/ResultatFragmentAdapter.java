package ca.qc.jeuxdegenie.jdgmobile.view;
import java.util.List;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ca.qc.jeuxdegenie.jdgmobile.R;
import ca.qc.jeuxdegenie.jdgmobile.model.competition.Result;

/**
	 * 
	 * @description Adapter to for the ExpandableListView
	 */
	public class ResultatFragmentAdapter extends BaseExpandableListAdapter {

		private Fragment context;
		private List<Result> groups;
        private List<List<Result>> children;
        
		public ResultatFragmentAdapter(Fragment context, List<Result> groups, List<List<Result>> children) {
			this.context = context;
			this.groups = groups;
			this.children = children;
		}
		
		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return children.get(groupPosition).get(childPosition);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			 
	        if (convertView == null) {
	            LayoutInflater infalInflater = (LayoutInflater) this.context.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            convertView = infalInflater.inflate(R.layout.resultat_list_children, null);
	        }
	        
	        TextView txtExpListChild = (TextView) convertView.findViewById(R.id.txtListChildren);
	        txtExpListChild.setText(getChild(groupPosition, childPosition).toString());
	        ImageView imgIconChildren = (ImageView) convertView.findViewById(R.id.imgIconChildren);
	        imgIconChildren.setImageResource(R.drawable.chevron_normal);
	        
	        return convertView;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return children.get(groupPosition).size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			return groups.get(groupPosition);
		}

		@Override
		public int getGroupCount() {
			return groups.size();
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			
			if (convertView == null) {
	            LayoutInflater infalInflater = (LayoutInflater) this.context.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            convertView = infalInflater.inflate(R.layout.resultat_list_groups, null);
	        }
	 
	        TextView txtExpListGroup = (TextView) convertView.findViewById(R.id.txtExpListGroup);
	        txtExpListGroup.setTypeface(null, Typeface.BOLD);
	        txtExpListGroup.setText(getGroup(groupPosition).toString());
	         
			
            return convertView;
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
	}