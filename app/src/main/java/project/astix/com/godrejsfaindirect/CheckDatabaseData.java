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
	 LinearLayout ll_todayInvoice,ll_todayCllctn;
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
        inflateStoreData();
	}
	

	private void getAllStoreListDetail() 
	{
		hmapStoreCollection=dbengine.fetch_StoreInvoiceWiseCollection_List();
		hmapStoreInvoice=dbengine.fetch_StoreInvoiceWiseData_List();
			

	    	
			
		}
	
	public void initialization()
	{
		
		 ll_todayCllctn=(LinearLayout)findViewById(R.id.ll_todayCllctn);
		 ll_todayInvoice=(LinearLayout)findViewById(R.id.ll_todayInvoice);
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
		
		



	}

	public void inflateStoreData()
	{
		ll_todayInvoice.removeAllViews();
		if(hmapStoreInvoice!=null && hmapStoreInvoice.size()>0)
		{
			for(Map.Entry<String,ArrayList<String>> entryInvoice:hmapStoreInvoice.entrySet())
			{
				View viewStoreInfo=getLayoutInflater().inflate(R.layout.store_report_info, null);
				TextView txt_StoreName= (TextView) viewStoreInfo.findViewById(R.id.txt_StoreName);
				LinearLayout ll_reportDesc= (LinearLayout) viewStoreInfo.findViewById(R.id.ll_reportDesc);
				txt_StoreName.setText(entryInvoice.getKey().split(Pattern.quote("^"))[1]);
				TextView txtInvoiceNumHdr=getTextView("Invoice Number",true);
				TextView txtInvoiceValHdr=getTextView("Invoice Value",true);
				//TextView txtInvoiceStatusHdr=getTextView("Status",true);
				//txtInvoiceStatusHdr
				LinearLayout ll_headerForInvoiceHdr=getLinearLayoutHorizontal2Desc(txtInvoiceNumHdr,txtInvoiceValHdr);
				ll_reportDesc.addView(ll_headerForInvoiceHdr);
				ArrayList<String> listStrInvcDtls=entryInvoice.getValue();
				if(listStrInvcDtls!=null && listStrInvcDtls.size()>0)
				{
					for(String invoiceInfo:listStrInvcDtls)
					{
						TextView txtInvoiceNum=getTextView(invoiceInfo.split(Pattern.quote("^"))[0],false);
						TextView txtInvoiceVal=getTextView(invoiceInfo.split(Pattern.quote("^"))[1],false);
						//TextView txtInvoiceStatus=getTextView(invoiceInfo.split(Pattern.quote("^"))[2],false);
						//txtInvoiceStatus
						LinearLayout ll_headerForInvoice=getLinearLayoutHorizontal2Desc(txtInvoiceNum,txtInvoiceVal);
						ll_reportDesc.addView(ll_headerForInvoice);
					}
				}

				ll_todayInvoice.addView(viewStoreInfo);
			}
		}

		if(hmapStoreCollection!=null && hmapStoreCollection.size()>0)
		{
			for(Map.Entry<String,ArrayList<String>> entryCollection:hmapStoreCollection.entrySet())
			{
				View viewStoreInfo=getLayoutInflater().inflate(R.layout.store_report_info, null);
				TextView txt_StoreName= (TextView) viewStoreInfo.findViewById(R.id.txt_StoreName);
				LinearLayout ll_reportDesc= (LinearLayout) viewStoreInfo.findViewById(R.id.ll_reportDesc);
				txt_StoreName.setText(entryCollection.getKey().split(Pattern.quote("^"))[1]);
				TextView txtInvoiceNumHdr=getTextView("Collection Code",true);
				TextView txtInvoiceValHdr=getTextView("Collection Value",true);
				//TextView txtInvoiceStatusHdr=getTextView("Status",true);
				//txtInvoiceStatusHdr
				LinearLayout ll_headerForInvoiceHdr=getLinearLayoutHorizontal2Desc(txtInvoiceNumHdr,txtInvoiceValHdr);
				ll_reportDesc.addView(ll_headerForInvoiceHdr);
				ArrayList<String> listStrInvcDtls=entryCollection.getValue();
				if(listStrInvcDtls!=null && listStrInvcDtls.size()>0)
				{
					for(String invoiceInfo:listStrInvcDtls)
					{
						TextView txtInvoiceNum=getTextView(invoiceInfo.split(Pattern.quote("^"))[0],false);
						TextView txtInvoiceVal=getTextView(invoiceInfo.split(Pattern.quote("^"))[1],false);
						//TextView txtInvoiceStatus=getTextView(invoiceInfo.split(Pattern.quote("^"))[2],false);
						//txtInvoiceStatus
						LinearLayout ll_headerForCollection=getLinearLayoutHorizontal2Desc(txtInvoiceNum,txtInvoiceVal);
						ll_reportDesc.addView(ll_headerForCollection);
					}
				}

				ll_todayCllctn.addView(viewStoreInfo);
			}
		}




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
