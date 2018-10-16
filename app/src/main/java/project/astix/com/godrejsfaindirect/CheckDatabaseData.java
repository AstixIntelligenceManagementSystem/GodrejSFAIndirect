package project.astix.com.godrejsfaindirect;





import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.astix.Common.CommonInfo;

import org.w3c.dom.Text;

public class CheckDatabaseData extends BaseActivity
{

	SharedPreferences pref;
	ArrayAdapter<String> dataAdapter = null;
	 String[] storeNames;
	 RecyclerView rcyclrVw_InvcCllctnDetails;
	 TextView txt_lsc,txt_lstSyncdData,txt_lstInvcSyncd,txt_lstVisitTime;
	 LinkedHashMap<String, String> hmapStore_details=new LinkedHashMap<String, String>();
	LinkedHashMap<String, ArrayList<String>> hmapStoreCollection;
	LinkedHashMap<String, ArrayList<String>> hmapStoreInvoice;
	 PRJDatabase dbengine = new PRJDatabase(this);
	 
	 String date_value="";
		String imei="";
		String rID;
		String pickerDate="";

		public String back="0";
		public int bck = 0;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		 setContentView(R.layout.activity_show_data);

		 Intent extras = getIntent();
			bck = extras.getIntExtra("bck", 0);
			
			
			if(extras !=null)
			{
			date_value=extras.getStringExtra("userDate");
			pickerDate= extras.getStringExtra("pickerDate");
			imei=extras.getStringExtra("imei");
			rID=extras.getStringExtra("rID");
			back=extras.getStringExtra("back");	
			
		    }
		pref=getSharedPreferences(CommonInfo.LastTrackPreference,MODE_PRIVATE);
		 getAllStoreListDetail();
		 
		 initialization();
      //  inflateStoreData();
	}
	

	private void getAllStoreListDetail() 
	{
		//hmapStoreCollection=dbengine.fetch_StoreInvoiceWiseCollection_List();
		//hmapStoreInvoice=dbengine.fetch_StoreInvoiceWiseData_List();
		hmapStoreInvoice=dbengine.fnCheckInvoiceCollectionReports();
			

	    	
			
		}
	
	public void initialization()
	{

		rcyclrVw_InvcCllctnDetails=(RecyclerView) findViewById(R.id.rcyclrVw_InvcCllctnDetails);

		 txt_lsc=(TextView) findViewById(R.id.txt_lsc);
		 txt_lstSyncdData=(TextView)findViewById(R.id.txt_lstSyncdData);
		 txt_lstInvcSyncd=(TextView) findViewById(R.id.txt_lstInvcSyncd);
		txt_lstVisitTime=(TextView) findViewById(R.id.txt_lstVisitTime);
		 if(pref.contains("StockSyncData"))
		 {
			 txt_lsc.setText(": "+pref.getString("StockSyncData","NA"));
		 }
		 if(pref.contains("LastInvoice"))
		 {
			 txt_lstSyncdData.setText(": "+pref.getString("LastInvoice","NA"));
		 }
		 if(pref.contains("LastSync"))
		 {
			 txt_lstInvcSyncd.setText(": "+pref.getString("LastSync","NA"));
		 }

		if(pref.contains("LastVisit"))
		{
			txt_lstVisitTime.setText(": "+pref.getString("LastVisit","NA"));
		}

		ImageView but_back=(ImageView)findViewById(R.id.backbutton);






		but_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent ide=new Intent(CheckDatabaseData.this,StoreSelection.class);
				ide.putExtra("userDate", date_value);
				ide.putExtra("pickerDate", pickerDate);
				ide.putExtra("imei", imei);
				ide.putExtra("rID", rID);
				//startActivity(ide);
				startActivity(ide);
				finish();
				
				
			}
		});


		InvoiceCollectionInfoAdapter mAdapter = new InvoiceCollectionInfoAdapter(CheckDatabaseData.this,hmapStoreInvoice);
		RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
		rcyclrVw_InvcCllctnDetails.setLayoutManager(mLayoutManager);
		rcyclrVw_InvcCllctnDetails.setItemAnimator(new DefaultItemAnimator());
		rcyclrVw_InvcCllctnDetails.setAdapter(mAdapter);


	}


	public TextView getTextView(String desc,boolean isHeader)
	{


		TextView txtVw_ques=new TextView(CheckDatabaseData.this);
		LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, 1f);
		txtVw_ques.setLayoutParams(layoutParams1);


		txtVw_ques.setGravity(Gravity.CENTER);
		if(isHeader)
		{
			txtVw_ques.setTextColor(getResources().getColor(R.color.black));
		}
		else
		{
			txtVw_ques.setTextColor(getResources().getColor(R.color.blue));
		}

		txtVw_ques.setText(desc);


		return txtVw_ques;
	}


	//, TextView lay3
	private LinearLayout getLinearLayoutHorizontal2Desc(TextView lay1, TextView lay2) {
		LinearLayout lay = new LinearLayout(CheckDatabaseData.this);

		lay.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, 1f);
		lay1.setLayoutParams(layoutParams1);
		LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, 1f);
		//layoutParams2.setMargins(10, 0, 0, 0);
		lay2.setLayoutParams(layoutParams2);
		LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, 1f);
		//layoutParams2.setMargins(10, 0, 0, 0);
		//lay3.setLayoutParams(layoutParams3);
		lay.addView(lay1);
		lay.addView(lay2);
		//lay.addView(lay3);


		return lay;

	}
	
	
}
