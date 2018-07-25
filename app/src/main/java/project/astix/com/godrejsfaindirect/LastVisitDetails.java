package project.astix.com.godrejsfaindirect;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Pattern;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.PowerManager;
import android.provider.Settings;
import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.astix.Common.CommonInfo;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class LastVisitDetails extends BaseActivity implements LocationListener,GoogleApiClient.ConnectionCallbacks,
		GoogleApiClient.OnConnectionFailedListener
{

	int flgNoInvoiceButtonClick=0,ReasonIdForNoInvoice=0;
	LinkedHashMap<String, String> hmapReasonForNoSales ;
	String TmpInvoiceCodePDA;
	public String newfullFileName;
	DatabaseAssistant DA = new DatabaseAssistant(this);
	/* For Location Srvices Start*/
	public String VisitTimeInSideStore="NA";
	public  int flgRestartOrderReview=0;
	public  int flgStoreOrderOrderReview=0;

	String fusedData;
	String mLastUpdateTime;
	int countSubmitClicked=0;
	Location mCurrentLocation;
	GoogleApiClient mGoogleApiClient;
	LocationRequest mLocationRequest;
	boolean isGPSEnabled = false;
	boolean isNetworkEnabled = false;
	PowerManager pm;
	LocationListener locationListener;
	double latitude;
	double longitude;
	public LocationManager locationManager;
	public Location location;
	// PowerManager.WakeLock wl;
	public ProgressDialog pDialog2STANDBY;

	public int butClickForGPS=0;
	public String FusedLocationLatitude="0";
	public String FusedLocationLongitude="0";
	public String FusedLocationProvider="";
	public String FusedLocationAccuracy="0";
	public String GPSLocationLatitude="0";
	public String GPSLocationLongitude="0";
	public String GPSLocationProvider="";
	public String GPSLocationAccuracy="0";
	public String NetworkLocationLatitude="0";
	public String NetworkLocationLongitude="0";
	public String NetworkLocationProvider="";
	public String NetworkLocationAccuracy="0";
	public AppLocationService appLocationService;
	public CoundownClass2 countDownTimer2;
	public String fnAccurateProvider="";
	public String fnLati="0";
	public String fnLongi="0";
	public Double fnAccuracy=0.0;


	private static final String TAG = "LocationActivity";
	private static final long INTERVAL = 1000 * 10;
	private static final long FASTEST_INTERVAL = 1000 * 5;
	private static final long MIN_TIME_BW_UPDATES = 1000  * 1; //1 second
	private final long startTime = 31000;
	private final long interval = 10000;

	public  int flgLocationServicesOnOffOrderReview=0;
	public  int flgGPSOnOffOrderReview=0;
	public  int flgNetworkOnOffOrderReview=0;
	public  int flgFusedOnOffOrderReview=0;
	public  int flgInternetOnOffWhileLocationTrackingOrderReview=0;

	public int powerCheck=0;
	public  PowerManager.WakeLock wl;
	/* Fro Location Services Ends*/

	Double outstandingvalue=0.0;
	boolean isStrCls=false;
	public int flgVisitCollectionMarkedStatus=0;
	public static int battLevel=0;
	public  LinearLayout ll_gstDetails,ll_gstDependent;
	public  RadioButton rb_gst_yes,rb_gst_no,rb_pending;
	public  EditText edit_gstYes;
	public Button gst_Details_but,btn_CloseStore;
	public String StoreVisitCode="NA";
	public  ArrayList<String> GstComplianceData=new  ArrayList<String>();
		 
	public  String flagforGstLayout="",flagforGstRadio="";
	public  String GstYesValue="";
		 
	public String flgGSTCapture="";
	public String flgGSTCompliance="";
	public  String GSTNumberGlobal="";
	
	public int chkIfStoreFasQuote=0;
	public int chkIfStoreAllowedQuote=1;
	public String storeID;
	public String imei;
	public String date;
	public String pickerDate;
	public Double currLon;
	public Double currLat;
	public String selStoreName;
	public String startTS;
	public int bck = 0;
	public int checkdataForVisit=0;

	public float acc;
	public Double myCurrentLon;
	public Double myCurrentLat;
	public TableLayout tbl4_dyntable_dynprodtableQuatation;
	public TableRow tr2PG4;
	public TableLayout tbl1_dyntable_For_OrderDetails;
	public TableRow tr1PG2;
	public TableLayout tbl3_dyntable_SchemeApplicable;
	public TableRow tr2PG2;
	public TableLayout tbl3_dyntable_SpecialSchemeApplicable;
	public TableRow tr2PG2_SpecialScheme;
	public String Noti_text="Null";
	public int MsgServerID=0;
	public TableLayout tbl2_dyntable_For_LastVisitDate;
	public TableRow tr3PG2;
	public TableLayout tbl1_dyntable_For_ExecutionDetails;
	public TableRow ExecutionRow;
	public String lastVisitDate="";
	public String lastOrderDate="";
	LinkedHashMap<String, String> hmapStoreBasicDetails=new LinkedHashMap<String, String>();
	String[] strInvoiceData;
	LinearLayout ll_InvoiceLastVisit,ll_inflateInvoiceData;
	 LinkedHashMap<String, String> hmapDistinctSalesQuotePersonMeetMstr=new LinkedHashMap<String, String>();
	Float locACC;
	 LinkedHashMap<String, String> hmapAllValuesOfPaymentMode;
	 CheckBox chBoxView,AdvanceBeforeDeliveryCheckBoxNew,OnDeliveryCheckBoxNew,CreditCheckBoxNew;
	 LinearLayout ll_data,parentOfAdvanceBeforeDeliveryPayMentMode,parentOfOnDeliveryPayMentMode,parentOfCreditPayMentMode,parentOfCheckBox;
	 TextView PaymentStageTextView,paymentModeTextviewNew,creditdaysTextboxNew,CreditlimitTextboxNew,percentageTextviewNew,paymentstagetextviewNew, CreditDaysTextbox, PaymentModeTextView, Date,SalesQuoteTypeSpinner,ValFrom,ValTo,SalesQuoteType,ValidityFrom,PaymentTerms,headerstring;
	 EditText percentageOfAdvanceBeforeDelivery,percentageOfOnDelivery,creditDaysOfOnDelivery,PercentageOfCredit,creditDaysOfCredit,creditLimitOfCredit;
	 LinkedHashMap<String,String> hmapZoneDisplayMstr;
	 StoreSelection aa;
	
	Locale locale  = new Locale("en", "UK");
	String pattern = "###.##";
	DecimalFormat decimalFormat = (DecimalFormat)NumberFormat.getNumberInstance(locale);
	TextView tv_outstandingvalue;

	PRJDatabase dbengine = new PRJDatabase(this);
	private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context arg0, Intent intent) {

			battLevel =intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);

		}
	};

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		try
		{

		dbengine.open();
		String Noti_textWithMsgServerID=dbengine.fetchNoti_textFromtblNotificationMstr();
		dbengine.close();
		System.out.println("Sunil Tty Noti_textWithMsgServerID :"+Noti_textWithMsgServerID);
		if(!Noti_textWithMsgServerID.equals("Null"))
		{
		StringTokenizer token = new StringTokenizer(String.valueOf(Noti_textWithMsgServerID), "_");

		MsgServerID= Integer.parseInt(token.nextToken().trim());
		Noti_text= token.nextToken().trim();


		if(Noti_text.equals("") || Noti_text.equals("Null"))
		{

		}
		else
		{
			 final AlertDialog builder = new AlertDialog.Builder(LastVisitDetails.this).create();

				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		        View openDialog = inflater.inflate(R.layout.custom_dialog, null);
		        openDialog.setBackgroundColor(Color.parseColor("#ffffff"));

		        builder.setCancelable(false);
		     	TextView header_text=(TextView)openDialog. findViewById(R.id.txt_header);
		     	final TextView msg=(TextView)openDialog. findViewById(R.id.msg);

				final Button ok_but=(Button)openDialog. findViewById(R.id.but_yes);
				final Button cancel=(Button)openDialog. findViewById(R.id.but_no);

				cancel.setVisibility(View.GONE);
			    header_text.setText(getText(R.string.AlertDialogHeaderMsg));
			     msg.setText(Noti_text);

			     	ok_but.setText(getText(R.string.AlertDialogOkButton));

					builder.setView(openDialog,0,0,0,0);

			        ok_but.setOnClickListener(new OnClickListener()
			        {

						@Override
						public void onClick(View arg0)
						{
							// TODO Auto-generated method stub

							long syncTIMESTAMP = System.currentTimeMillis();
							Date dateobj = new Date(syncTIMESTAMP);
							SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss",Locale.ENGLISH);
							String Noti_ReadDateTime = df.format(dateobj);

							dbengine.open();
							dbengine.updatetblNotificationMstr(MsgServerID,Noti_text,0,Noti_ReadDateTime,3);
							dbengine.close();

							try
							{
								dbengine.open();
							    int checkleftNoti=dbengine.countNumberOFNotificationtblNotificationMstr();
							    if(checkleftNoti>0)
							    {
								    	String Noti_textWithMsgServerID=dbengine.fetchNoti_textFromtblNotificationMstr();
										System.out.println("Sunil Tty Noti_textWithMsgServerID :"+Noti_textWithMsgServerID);
										if(!Noti_textWithMsgServerID.equals("Null"))
										{
											StringTokenizer token = new StringTokenizer(String.valueOf(Noti_textWithMsgServerID), "_");

											MsgServerID= Integer.parseInt(token.nextToken().trim());
											Noti_text= token.nextToken().trim();

											dbengine.close();
											if(Noti_text.equals("") || Noti_text.equals("Null"))
											{

											}
											else
											{
												  msg.setText(Noti_text);
											}
										}

							    }
								else
								{
									builder.dismiss();
								}

							}
							catch(Exception e)
							{

							}
				            finally
				            {
				            	dbengine.close();

				            }


						}
					});




			     	builder.show();






		}
		}
		}
		catch(Exception e)
		{

		}
	}

	public void showSettingsAlert(){
		  AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

		        // Setting Dialog Title
		        alertDialog.setTitle(R.string.genTermInformation);
		        alertDialog.setIcon(R.drawable.error_info_ico);

		        // Setting Dialog Message
		        alertDialog.setMessage(R.string.genTermGPSDisablePleaseEnable);

		        // On pressing Settings button
		        alertDialog.setPositiveButton(getText(R.string.AlertDialogOkButton), new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog,int which) {
		             Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		             startActivity(intent);
		            }
		        });

		        // Showing Alert Message
		        alertDialog.show();
		 }
		
	public void StoreNameAndSalesPersonInfo()
	{
		hmapStoreBasicDetails=dbengine.fngetStoreBasicDetails(storeID);
		TextView storeName = (TextView)findViewById(R.id.txt_storeSummary);
		TextView txt_SalesPersonName_Value = (TextView)findViewById(R.id.txt_SalesPersonName_Value);
		TextView txt_SalesPersonContact_Value = (TextView)findViewById(R.id.txt_SalesPersonContact_Value);

		TextView txt_StoreOwner_Value = (TextView)findViewById(R.id.txt_StoreOwner_Value);
		TextView txt_StoreOwnerContact_Value = (TextView)findViewById(R.id.txt_StoreOwnerContact_Value);

		TextView txt_StoreCatType_Value = (TextView)findViewById(R.id.txt_StoreCatType_Value);

		storeName.setText(hmapStoreBasicDetails.get("StoreName")+" "+getText(R.string.Summary));
		txt_StoreOwner_Value.setText(hmapStoreBasicDetails.get("OwnerName"));
		txt_StoreOwnerContact_Value.setText(hmapStoreBasicDetails.get("StoreContactNo"));
		txt_StoreCatType_Value.setText(hmapStoreBasicDetails.get("StoreCatType"));
		txt_SalesPersonName_Value.setText(hmapStoreBasicDetails.get("SalesPersonName"));
		txt_SalesPersonContact_Value.setText(hmapStoreBasicDetails.get("SalesPersonContact"));
	}

	public String  getStoreVisitCode()
	{
		int StoreCurrentOutsStat=dbengine.fnGetStoreCurrentOutsStat(storeID);
		if(StoreCurrentOutsStat!=1)
		{
			String passdLevel = battLevel + "%";
			StoreVisitCode=genStoreVisitCode();
			dbengine.fnInsertOrUpdate_tblStoreVisitMstr(StoreVisitCode,storeID,1,getDateInMonthTextFormat(),"0","0",getDateAndTimeInMilliSecond(),getDateAndTimeInMilliSecond(),getDateAndTimeInMilliSecond(),getDateAndTimeInMilliSecond(),"","0",passdLevel,0,0,0,0,0,0,0,0,0,0,0,0,0,0,flgVisitCollectionMarkedStatus,flgNoInvoiceButtonClick,ReasonIdForNoInvoice);

			//dbengine.UpdateStoreVisitBattVisitWise(storeID,passdLevel,StoreVisitCode);
		}
		else
			{
			StoreVisitCode=dbengine.fnGetStoreVisitCode(storeID);
		}
		return StoreVisitCode;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_last_visit_details);

		CommonInfo.ActiveRouteSM="0";


		registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		locationManager=(LocationManager) this.getSystemService(LOCATION_SERVICE);

		boolean isGPSok = false;
		boolean isNWok=false;
		isGPSok = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		isNWok = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

		if(!isGPSok)
		{
			isGPSok = false;
		}
		if(!isNWok)
		{
			isNWok = false;
		}
		if(!isGPSok && !isNWok)
		{
			try
			{
				showSettingsAlert();
			}
			catch(Exception e)
			{

			}

			isGPSok = false;
			isNWok=false;
		}

		decimalFormat.applyPattern(pattern);
		Intent passedvals = getIntent();
		bck = passedvals.getIntExtra("bck", 0);
		// aa=(StoreSelection)getIntent().getSerializableExtra("MyClass");
		
		if(bck == 1)
		{
			selStoreName = passedvals.getStringExtra("SN");
			storeID = passedvals.getStringExtra("storeID");
			imei = passedvals.getStringExtra("imei");
			date = passedvals.getStringExtra("userdate");
			pickerDate= passedvals.getStringExtra("pickerDate");
		}
		else
		{
			storeID = passedvals.getStringExtra("storeID");
			imei = passedvals.getStringExtra("imei");
			date = passedvals.getStringExtra("userDate");
			pickerDate= passedvals.getStringExtra("pickerDate");
			locACC = passedvals.getFloatExtra("acc", 0);
			currLon = passedvals.getDoubleExtra("currUsrLon", 0);
			currLat = passedvals.getDoubleExtra("currUsrLat", 0);
			selStoreName = passedvals.getStringExtra("selStoreName");
			startTS = passedvals.getStringExtra("startTS");
		}


		/*IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		Intent batteryStatus = getApplicationContext().registerReceiver(null, ifilter);*/
		StoreNameAndSalesPersonInfo();
		long syncTIMESTAMP;
		String fullTS;
		
		syncTIMESTAMP = System.currentTimeMillis();
		Date dateobj = new Date(syncTIMESTAMP);
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);
		fullTS = df.format(dateobj);
		
		ll_InvoiceLastVisit=(LinearLayout) findViewById(R.id.ll_InvoiceLastVisit);
		ll_inflateInvoiceData=(LinearLayout) findViewById(R.id.ll_inflateInvoiceData);

		    /*LinkedHashMap<String, String> hmapListQuoteISOfUnmappedWithProducts= dbengine.fnGetListQuoteISOfUnmappedWithProducts(storeID);
		    if(hmapListQuoteISOfUnmappedWithProducts.size()>0)
		    {
		    	for(Map.Entry<String, String> entry:hmapListQuoteISOfUnmappedWithProducts.entrySet())
				{
		    	dbengine.fndeleteQuoteISOfUnmappedWithProducts(entry.getKey());
				}
		    }
		    */
		/*int StoreCurrentOutsStat=dbengine.fnGetStoreCurrentOutsStat(storeID);

			if(StoreCurrentOutsStat!=1)
			{
				StoreVisitCode=genStoreVisitCode();
			}
			else {
				StoreVisitCode=dbengine.fnGetStoreVisitCode(storeID);
			}*/


			Button quotationBtn=	(Button) findViewById(R.id.quotationBtn);
		quotationBtn.setVisibility(View.GONE);
			/*chkIfStoreFasQuote=dbengine.fnchkIfStoreFasQuote(storeID);
			if(chkIfStoreFasQuote==1)
			{
				dbengine.fnDeletefromtblSchemeStoreMappingAgainstStore(storeID);
			}
			
			chkIfStoreAllowedQuote=dbengine.fnchkIfStoreAllowQuotation(storeID);
			if(chkIfStoreAllowedQuote==0)
			{
				quotationBtn.setVisibility(View.GONE);
			}*/
		
		/*TextView tvPreCreditAmt = (TextView)findViewById(R.id.pre_credit_amount_value);
		TextView tvBalAmt = (TextView)findViewById(R.id.pre_balance_amount_value);
		
	
		dbengine.open();
		Double PreCreditAmt =dbengine.fnGetLastCreditAmountFromLastInvoiceTable(storeID);
		dbengine.close();
		
			Double.parseDouble(decimalFormat.format(PreCreditAmt));//Double.parseDouble(new DecimalFormat("##.##").format(PreCreditAmt));
		
		
			tvPreCreditAmt.setText(""+PreCreditAmt);
			dbengine.open();
		String BalAmt =dbengine.fnGetPDALastInvoiceDetDueAmt(storeID);
		dbengine.close();
		Double BalAmtNew=0.00;
		if(BalAmt.equals(""))
		{
			BalAmt="0.00";
		}
		BalAmtNew=Double.parseDouble(BalAmt);
		if(BalAmtNew>0.0)
		{
		BalAmtNew = Double.parseDouble(new DecimalFormat("##.##").format(BalAmtNew));
		}
		Double.parseDouble(decimalFormat.format(BalAmtNew));
		tvBalAmt.setText(""+ BalAmtNew);*/
		
		/*dbengine.open();
		String lastVisitDate=dbengine.fnGettblFirstOrderDetailsOnLastVisitDetailsActivity(storeID);
		dbengine.close();*/
		
		dbengine.open();
	    checkdataForVisit=dbengine.counttblForPDAGetLastVisitDate(storeID);
		dbengine.close();
		
		 TextView txt_visitDate_Value = (TextView)findViewById(R.id.txt_visitDate_Value);
		hmapReasonForNoSales=dbengine.fetch_ReasonForNoSales();
		if(checkdataForVisit==1)
		{
		dbengine.open();
		//nitish

		String lastVisitDateAndFlgOrder=dbengine.fnGetVisitDateAndflgOrderFromtblForPDAGetLastVisitDate(storeID);
		StringTokenizer tokens = new StringTokenizer(String.valueOf(lastVisitDateAndFlgOrder), "^");
		
		lastVisitDate=tokens.nextToken().trim();
		int flgOrder=Integer.parseInt(tokens.nextToken().trim());
		
		if(flgOrder==1)
		{
			txt_visitDate_Value.setBackgroundColor(Color.YELLOW);
		}
		else
		{
			txt_visitDate_Value.setBackgroundColor(Color.RED);
		}
		
		dbengine.close();
		}
		
		if(lastVisitDate.equals(""))
		{
			txt_visitDate_Value.setText("NA");
			
		}
		else if(lastVisitDate.equals("0"))
		{
			txt_visitDate_Value.setText("NA");
		}
		else if(lastVisitDate.equals("NA"))
		{
			txt_visitDate_Value.setText("NA");
		}
		else
		{
			txt_visitDate_Value.setText(lastVisitDate);
			
		}
		
final Button btn_Cancel=(Button) findViewById(R.id.btn_Cancel);
		
		int valSstatValueAgainstStore=0;
		 try
		 {
		dbengine.open();
		valSstatValueAgainstStore=dbengine.fnGetStatValueagainstStore(storeID);
		dbengine.close();
		if(valSstatValueAgainstStore==0)
		{
			btn_Cancel.setVisibility(View.VISIBLE);
		}
		else
		{
			btn_Cancel.setVisibility(View.GONE);
			
		}
		
		 }catch(Exception e)
		 {
			 
		 }finally
		 {
		
		 }

		btn_Cancel.setOnClickListener(new OnClickListener() {
		    //  wer
		      @Override
		      public void onClick(View v) 
		      {
		       // TODO Auto-generated method stub
		    	  
		    	  
		    	  

		     
		     AlertDialog.Builder alertDialogSyncError = new AlertDialog.Builder(LastVisitDetails.this);
		     alertDialogSyncError.setTitle(getText(R.string.genTermInformation));
		     alertDialogSyncError.setCancelable(false);  // try submitting the details from outside the door
		     alertDialogSyncError.setMessage(getText(R.string.CancelVisitDealer));
		     alertDialogSyncError.setPositiveButton(getText(R.string.AlertDialogOkButton),
		       new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which)
		        {
		        
		        	int flag=0;
					//getStoreVisitCode();
					String passdLevel = battLevel + "%";
					dbengine.UpdateStoreVisitBattVisitWise(storeID,passdLevel,StoreVisitCode);
		    		
		    		
		        	Intent storeSaveIntent = new Intent(LastVisitDetails.this, LauncherActivity.class);
		     		startActivity(storeSaveIntent);
		     		finish();
		        }
		       
		     });
		     alertDialogSyncError.setNeutralButton(getText(R.string.AlertDialogCancelButton),
		       new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) {

		         
		         dialog.dismiss();
		         
		        }
		       });
		     alertDialogSyncError.setIcon(R.drawable.info_ico);
		     
		     AlertDialog alert = alertDialogSyncError.create();
		     alert.show();
		     
		    
		    
		      }
		     });
		
		Button visitDate_butn=(Button)findViewById(R.id.txt_visitDate_Details);
		visitDate_butn.setOnClickListener(new OnClickListener(
				) {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub


				//getStoreVisitCode();
				dbengine.open();
				int checkDataForVisitDetails=dbengine.counttblForPDAGetLastVisitDetails(storeID);
				int checkDataForOrderDetails=dbengine.counttblForPDAGetLastOrderDetails(storeID);
				dbengine.close();
				String passdLevel = battLevel + "%";
				dbengine.UpdateStoreVisitBattVisitWise(storeID,passdLevel,StoreVisitCode);
				if(checkDataForVisitDetails!=0 || checkDataForOrderDetails!=0) 
				{
					Intent nxtP4 = new Intent(LastVisitDetails.this,LastVisitDetailsSecondPart.class);
					nxtP4.putExtra("storeID", storeID);
					nxtP4.putExtra("SN", selStoreName);
					nxtP4.putExtra("imei", imei);
					nxtP4.putExtra("userdate", date);
					nxtP4.putExtra("pickerDate", pickerDate);
					startActivity(nxtP4);
					finish();
				}
			else
			{
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LastVisitDetails.this);
				alertDialogBuilder.setTitle(getText(R.string.genTermInformation));
                alertDialogBuilder.setMessage(getText(R.string.NoDataAvlblForOrder));
				
				 alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton(getText(R.string.AlertDialogOkButton), new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {}
				});
				

				alertDialogBuilder.setIcon(R.drawable.info_ico);
		// create an alert dialog
		AlertDialog alert = alertDialogBuilder.create();
		alert.show();
			}
				
			}
		});
		
		//LastVisitDateValue
		
		
		
		TextView orderDate_Value = (TextView)findViewById(R.id.txt_orderDate_Value);
		
		dbengine.open();
	    int checkdataForOrder=dbengine.counttblForPDAGetLastOrderDate(storeID);
		dbengine.close();
		
		if(checkdataForOrder==1)
		{
		dbengine.open();
		String lastOrderDateAndflgExecutionSummary=dbengine.fnGettblForPDAGetLastOrderDate(storeID);
		dbengine.close();
		
		
		StringTokenizer tokens = new StringTokenizer(String.valueOf(lastOrderDateAndflgExecutionSummary), "^");
		
		lastOrderDate=tokens.nextToken().trim();
		int flgExecutionSummary=Integer.parseInt(tokens.nextToken().trim());
		
		if(flgExecutionSummary==1)
		{
			orderDate_Value.setBackgroundColor(Color.YELLOW);
		}
		else if(flgExecutionSummary==2)
		{
			orderDate_Value.setBackgroundColor(Color.RED);
		}
		else
		{
			orderDate_Value.setBackgroundColor(Color.MAGENTA);
		}
		
		
		
		}
		
		
		
		
		
		
		
		
		
		
		if(lastOrderDate.equals(""))
		{
			orderDate_Value.setText("NA");
			
		}
		else if(lastOrderDate.equals("0"))
		{
			orderDate_Value.setText("NA");
		}
		else if(lastOrderDate.equals("NA"))
		{
			orderDate_Value.setText("NA");
		}
		else
		{
			orderDate_Value.setText(lastOrderDate);
			
		}
		
	
		
		
		
		Button executionDetails_butn=(Button)findViewById(R.id.txt_execution_Details);
		executionDetails_butn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// TODO Auto-generated method stub
				
				LayoutInflater layoutInflater = LayoutInflater.from(LastVisitDetails.this);
				View promptView = layoutInflater.inflate(R.layout.lastsummary_execution, null);
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LastVisitDetails.this);
				

				alertDialogBuilder.setTitle(getText(R.string.genTermInformation));

				
				
				dbengine.open();
				
				String DateResult[]=dbengine.fetchOrderDateFromtblForPDAGetExecutionSummary(storeID);
				String LastexecutionDetail[]=dbengine.fetchAllDataFromtbltblForPDAGetExecutionSummary(storeID);
				
				String PrdNameDetail[]=dbengine.fetchPrdNameFromtblForPDAGetExecutionSummary(storeID);

				String ProductIDDetail[]=dbengine.fetchProductIDFromtblForPDAGetExecutionSummary(storeID);
				
				System.out.println("Ashish and Anuj LastexecutionDetail : "+LastexecutionDetail.length);
				dbengine.close();
				
				if(DateResult.length>0)
				{
					TextView FirstDate = (TextView)promptView.findViewById(R.id.FirstDate);
					 TextView SecondDate = (TextView)promptView.findViewById(R.id.SecondDate);
					 TextView ThirdDate = (TextView)promptView.findViewById(R.id.ThirdDate);
					 
					 TextView lastExecution = (TextView)promptView.findViewById(R.id.lastExecution);
					 lastExecution.setText(LastVisitDetails.this.getResources().getString(R.string.lastvisitdetails_last)+
							 DateResult.length+LastVisitDetails.this.getResources().getString(R.string.ExecSummary));

					if(DateResult.length==1)
					{
						FirstDate.setText(""+DateResult[0]);
						SecondDate.setVisibility(View.GONE);
						ThirdDate.setVisibility(View.GONE);
					}
					else if(DateResult.length==2)
					{
						FirstDate.setText(""+DateResult[0]);
						SecondDate.setText(""+DateResult[1]);
						ThirdDate.setVisibility(View.GONE);
					}
					else if(DateResult.length==3)
					{
						FirstDate.setText(""+DateResult[0]);
						SecondDate.setText(""+DateResult[1]);
						ThirdDate.setText(""+DateResult[2]);
					}
				}
				
				LayoutInflater inflater = getLayoutInflater();
				
				DisplayMetrics dm = new DisplayMetrics();
			    getWindowManager().getDefaultDisplay().getMetrics(dm);
			    double x = Math.pow(dm.widthPixels/dm.xdpi,2);
			    double y = Math.pow(dm.heightPixels/dm.ydpi,2);
			    double screenInches = Math.sqrt(x+y);
				if(LastexecutionDetail.length>0)
				{
					alertDialogBuilder.setView(promptView);

					tbl1_dyntable_For_ExecutionDetails = (TableLayout) promptView.findViewById(R.id.dyntable_For_ExecutionDetails);
					TableRow row1 = (TableRow)inflater.inflate(R.layout.table_execution_head, tbl1_dyntable_For_OrderDetails, false);
					
					TextView firstDateOrder = (TextView)row1.findViewById(R.id.firstDateOrder);
					TextView firstDateInvoice = (TextView)row1.findViewById(R.id.firstDateInvoice);
					TextView secondDateOrder = (TextView)row1.findViewById(R.id.secondDateOrder);
					TextView secondDateInvoice = (TextView)row1.findViewById(R.id.secondDateInvoice);
					TextView thirdDateOrder = (TextView)row1.findViewById(R.id.thirdDateOrder);
					TextView thirdDateInvoice = (TextView)row1.findViewById(R.id.thirdDateInvoice);
					if(DateResult.length>0)
					{
						if(DateResult.length==1)
						{
							
							secondDateOrder.setVisibility(View.GONE);
							secondDateInvoice.setVisibility(View.GONE);
							thirdDateOrder.setVisibility(View.GONE);
							thirdDateInvoice.setVisibility(View.GONE);
						}
						else if(DateResult.length==2)
						{
							thirdDateOrder.setVisibility(View.GONE);
							thirdDateInvoice.setVisibility(View.GONE);
						}
					}
					
					tbl1_dyntable_For_ExecutionDetails.addView(row1);
					
					
				for (int current = 0; current <= (PrdNameDetail.length - 1); current++) 
				{
					

					final TableRow row = (TableRow)inflater.inflate(R.layout.table_execution_row, tbl1_dyntable_For_OrderDetails, false);
					
					TextView tv1 = (TextView)row.findViewById(R.id.skuName);
					TextView tv2 = (TextView)row.findViewById(R.id.firstDateOrder);
					TextView tv3 = (TextView)row.findViewById(R.id.firstDateInvoice);
					TextView tv4 = (TextView)row.findViewById(R.id.secondDateOrder);
					TextView tv5 = (TextView)row.findViewById(R.id.secondDateInvoice);
					TextView tv6 = (TextView)row.findViewById(R.id.thirdDateOrder);
					TextView tv7 = (TextView)row.findViewById(R.id.thirdDateInvoice);
					
					tv1.setText(PrdNameDetail[current]);
					
					if(DateResult.length>0)
					{
						if(DateResult.length==1)
						{
							tv4.setVisibility(View.GONE);
							tv5.setVisibility(View.GONE);
							tv6.setVisibility(View.GONE);
							tv7.setVisibility(View.GONE);
								dbengine.open();
								String abc[]=dbengine.fetchAllDataNewFromtbltblForPDAGetExecutionSummary(storeID,DateResult[0],ProductIDDetail[current]);
								dbengine.close();
								
								//System.out.println("Check Value Number "+abc.length);
								//System.out.println("Check Value Number12 "+DateResult[0]);
									if(abc.length>0)
									{
									StringTokenizer tokens = new StringTokenizer(String.valueOf(abc[0]), "_");
									tv2.setText(tokens.nextToken().trim());
									tv3.setText(tokens.nextToken().trim());
									}
									else
									{
										tv2.setText("0");
										tv3.setText("0");
									}
							}
						else if(DateResult.length==2)
						{
							tv6.setVisibility(View.GONE);
							tv7.setVisibility(View.GONE);
							
							dbengine.open();
							String abc[]=dbengine.fetchAllDataNewFromtbltblForPDAGetExecutionSummary(storeID,DateResult[0],ProductIDDetail[current]);
							dbengine.close();
							
							//System.out.println("Check Value Number "+abc.length);
							//System.out.println("Check Value Number12 "+DateResult[0]);
								if(abc.length>0)
								{
								StringTokenizer tokens = new StringTokenizer(String.valueOf(abc[0]), "_");
								tv2.setText(tokens.nextToken().trim());
								tv3.setText(tokens.nextToken().trim());
								}
								else
								{
									tv2.setText("0");
									tv3.setText("0");
								}
								
								dbengine.open();
								String abc1[]=dbengine.fetchAllDataNewFromtbltblForPDAGetExecutionSummary(storeID,DateResult[1],ProductIDDetail[current]);
								dbengine.close();
								
								//System.out.println("Check Value Number NEw "+abc1.length);
								//System.out.println("Check Value Number12 NEw "+DateResult[1]);
									if(abc1.length>0)
									{
									StringTokenizer tokens = new StringTokenizer(String.valueOf(abc1[0]), "_");
									tv4.setText(tokens.nextToken().trim());
									tv5.setText(tokens.nextToken().trim());
									}
									else
									{
										tv4.setText("0");
										tv5.setText("0");
									}
								
								
							
							
							
						}
						else if(DateResult.length==3)
						{
							dbengine.open();
							String abc[]=dbengine.fetchAllDataNewFromtbltblForPDAGetExecutionSummary(storeID,DateResult[0],ProductIDDetail[current]);
							dbengine.close();
							
							//System.out.println("Check Value Number "+abc.length);
							//System.out.println("Check Value Number12 "+DateResult[0]);
								if(abc.length>0)
								{
								StringTokenizer tokens = new StringTokenizer(String.valueOf(abc[0]), "_");
								tv2.setText(tokens.nextToken().trim());
								tv3.setText(tokens.nextToken().trim());
								}
								else
								{
									tv2.setText("0");
									tv3.setText("0");
								}
								
								dbengine.open();
								String abc1[]=dbengine.fetchAllDataNewFromtbltblForPDAGetExecutionSummary(storeID,DateResult[1],ProductIDDetail[current]);
								dbengine.close();
								
								//System.out.println("Check Value Number NEw "+abc1.length);
								//System.out.println("Check Value Number12 NEw "+DateResult[1]);
									if(abc1.length>0)
									{
									StringTokenizer tokens = new StringTokenizer(String.valueOf(abc1[0]), "_");
									tv4.setText(tokens.nextToken().trim());
									tv5.setText(tokens.nextToken().trim());
									}
									else
									{
										tv4.setText("0");
										tv5.setText("0");
									}
									
									dbengine.open();
									String abc2[]=dbengine.fetchAllDataNewFromtbltblForPDAGetExecutionSummary(storeID,DateResult[2],ProductIDDetail[current]);
									dbengine.close();
									
									//System.out.println("Check Value Number NEw "+abc2.length);
									//System.out.println("Check Value Number12 NEw "+DateResult[2]);
										if(abc2.length>0)
										{
										StringTokenizer tokens = new StringTokenizer(String.valueOf(abc2[0]), "_");
										tv6.setText(tokens.nextToken().trim());
										tv7.setText(tokens.nextToken().trim());
										}
										else
										{
											tv6.setText("0");
											tv7.setText("0");
										}
								
								
							
							
							
						}
						else
						{
							
						}
					}
					
					/*if(screenInches>6.5)
					{
						tv1.setTextSize(14);
						tv2.setTextSize(14);
						tv3.setTextSize(14);
						tv4.setTextSize(14);
						tv5.setTextSize(14);
						tv6.setTextSize(14);
						tv7.setTextSize(14);
					}
					else
					{
						
					}*/
					
					//System.out.println("Abhinav Raj LTDdet[current]:"+LTDdet[current]);
					/*StringTokenizer tokens = new StringTokenizer(String.valueOf(LastexecutionDetail[current]), "_");
					
					tv1.setText(tokens.nextToken().trim());
					tv2.setText(tokens.nextToken().trim());
					tokens.nextToken().trim();
					tv3.setText(tokens.nextToken().trim());*/
					/*tv4.setText(tokens.nextToken().trim());
					tv5.setText(tokens.nextToken().trim());*/
					tbl1_dyntable_For_ExecutionDetails.addView(row);
					
				}
				
			}
				else
				{
					alertDialogBuilder.setMessage(getText(R.string.AlertExecNoSum));
				}
				alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton(getText(R.string.AlertDialogOkButton), new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {}
				});
				

				alertDialogBuilder.setIcon(R.drawable.info_ico);
		// create an alert dialog
		AlertDialog alert = alertDialogBuilder.create();
		alert.show();
				
			
				
			}
		});
		
		
		
		
		/*
		dbengine.open();
		int checkdata=dbengine.counttblSecondVisitDetailsOnLastVisitDetailsActivity(storeID);
		dbengine.close();
		
		if(checkdata==1)
		{
		dbengine.open();
		String lastVisitDate=dbengine.fnGettblSecondVisitDetailsOnLastVisitDetailsActivity(storeID);
		LastVisitDateValue.setText(lastVisitDate);
		dbengine.close();
		
		tbl2_dyntable_For_LastVisitDate = (TableLayout) findViewById(R.id.dyntable_For_LastVisitDate);
		
		dbengine.open();
		String LastVisitDetails[] = dbengine.fetchtblSecondVisitDetailsOnLastVisitDetailsActivity(storeID);
		dbengine.close();
		
		LayoutInflater inflater3 = getLayoutInflater();
		for (int current2 = 0; current2 <= (LastVisitDetails.length - 1); current2++) 
		{

			final TableRow row2 = (TableRow)inflater3.inflate(R.layout.trans_row3_second_pg, tbl2_dyntable_For_LastVisitDate, false);
			
			
			StringTokenizer tokens = new StringTokenizer(String.valueOf(LastVisitDetails[current2]), "_");
			
			TextView tv1 = (TextView)row2.findViewById(R.id.tv32ndPageProduct);
			TextView tv2 = (TextView)row2.findViewById(R.id.tv32ndPageTarget);
			if(screenInches>6.5)
			{
				tv1.setTextSize(14);
				tv2.setTextSize(14);
				
			}
			else
			{
				
			}
			
			tv1.setText(tokens.nextToken().trim());
			tv2.setText(tokens.nextToken().trim());
			
			
		
			tbl2_dyntable_For_LastVisitDate.addView(row2);
		}
	}
	else
	{
		TextView txtLastVisitDate=(TextView)findViewById(R.id.txtLastVisitDate);
		TextView LastVisitDateValue=(TextView)findViewById(R.id.LastVisitDateValue);
		RelativeLayout RelativeLayoutLastVisitDate=(RelativeLayout)findViewById(R.id.relativeLayoutLastVisitDate);
		txtLastVisitDate.setVisibility(View.GONE);
		LastVisitDateValue.setVisibility(View.GONE);
		RelativeLayoutLastVisitDate.setVisibility(View.GONE);
		TextView txtLastOrderDate=(TextView)findViewById(R.id.textView1_TSHeader);
		txtLastOrderDate.setText("Last Order/Visit Date : ");
	}*/
		
		
      tbl3_dyntable_SchemeApplicable = (TableLayout) findViewById(R.id.dyntable_SchemeApplicable);
		
		dbengine.open();
		String LTschApp[] = dbengine.PrevPDASchemeApplicableSecondPage(storeID);
		dbengine.close();
		
		if(LTschApp.length>0)
		{
			TextView txt_GeneralScheme_Value = (TextView)findViewById(R.id.txt_GeneralScheme_Value);
			txt_GeneralScheme_Value.setVisibility(View.GONE);
			RelativeLayout relLayout_for_generalScheme = (RelativeLayout)findViewById(R.id.relLayout_for_generalScheme);
			relLayout_for_generalScheme.setVisibility(View.VISIBLE);

		}
		else
		{
			TextView txt_GeneralScheme_Value = (TextView)findViewById(R.id.txt_GeneralScheme_Value);

			RelativeLayout relLayout_for_generalScheme = (RelativeLayout)findViewById(R.id.relLayout_for_generalScheme);
			relLayout_for_generalScheme.setVisibility(View.GONE);
            // set Visibility gone for Godrej Project Acc. to Avinash Sir
			//txt_GeneralScheme_Value.setVisibility(View.VISIBLE);
			txt_GeneralScheme_Value.setVisibility(View.GONE);
		}
		
		
		
		LayoutInflater inflater2 = getLayoutInflater();
		for (int current2 = 0; current2 <= (LTschApp.length - 1); current2++)
		{

			final TableRow row2 = (TableRow)inflater2.inflate(R.layout.trans_row2_second_pg, tbl3_dyntable_SchemeApplicable, false);
			

			StringTokenizer token = new StringTokenizer(String.valueOf(LTschApp[current2]), "_");
					
					String SchemeId=token.nextToken().toString().trim();
					String SchemeDesc=token.nextToken().toString().trim();
					
					TextView tv1 = (TextView)row2.findViewById(R.id.tv2PG2SCHDES);
					
					tv1.setTag(SchemeId+"_"+SchemeDesc);
					
					//tv1.setText(SchemeDesc);
					
					 tv1.setTextColor(Color.parseColor("#303F9F"));
				     tv1.setTypeface(null, Typeface.BOLD);
				    String mystring=SchemeDesc;
				    SpannableString content = new SpannableString(mystring);
				     content.setSpan(new UnderlineSpan(), 0, mystring.length(), 0);
				     tv1.setText(content);
					
					
					tv1.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							//v.getTag();
							
							CustomAlertBoxForSchemeDetails(v.getTag().toString());
							
							
							
						}
					});
				
				
		
			tbl3_dyntable_SchemeApplicable.addView(row2);
		}
		
    tbl3_dyntable_SpecialSchemeApplicable = (TableLayout) findViewById(R.id.dyntable_SpecialSchemeApplicable);
		
		dbengine.open();
		String SpecialScheme[] = dbengine.PrevPDASchemeApplicableSecondPageSpecialScheme(storeID);
		dbengine.close();
		
		if(SpecialScheme.length>0)
		{
			RelativeLayout relLayout_for_specialScheme = (RelativeLayout)findViewById(R.id.relLayout_for_specialScheme);
			relLayout_for_specialScheme.setVisibility(View.VISIBLE);
			TextView txt_specialScheme_Value = (TextView)findViewById(R.id.txt_specialScheme_Value);
			txt_specialScheme_Value.setVisibility(View.GONE);
			
			
		}
		else
		{
			RelativeLayout relLayout_for_specialScheme = (RelativeLayout)findViewById(R.id.relLayout_for_specialScheme);
			relLayout_for_specialScheme.setVisibility(View.GONE);
			TextView txt_specialScheme_Value = (TextView)findViewById(R.id.txt_specialScheme_Value);

			// set Visibility gone for Godrej Project Acc. to Avinash Sir
			//txt_specialScheme_Value.setVisibility(View.VISIBLE);
			txt_specialScheme_Value.setVisibility(View.GONE);

		}
		
		LayoutInflater inflater2_SpecialScheme = getLayoutInflater();
		for (int current2 = 0; current2 <= (SpecialScheme.length - 1); current2++)
		{

			final TableRow row2 = (TableRow)inflater2_SpecialScheme.inflate(R.layout.trans_row2_second_pg, tbl3_dyntable_SpecialSchemeApplicable, false);
			
			TextView tv1 = (TextView)row2.findViewById(R.id.tv2PG2SCHDES);
		
			tv1.setText(SpecialScheme[current2].toString().trim());
			/*if(screenInches>6.5)
			{
				tv1.setTextSize(14);
				
			}
			else
			{
				
			}*/
		
			tbl3_dyntable_SpecialSchemeApplicable.addView(row2);
		}
		
		
	
		Button nxtP4 = (Button)findViewById(R.id.nxtP4);
		Button prevP2 = (Button)findViewById(R.id.prevP2);
	
		
		prevP2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub


			//	getStoreVisitCode();
				String passdLevel = battLevel + "%";
				dbengine.UpdateStoreVisitBattVisitWise(storeID,passdLevel,StoreVisitCode);
				dbengine.open();
				String rID=dbengine.GetActiveRouteID();
				dbengine.close();
				
				Intent prevP2 = new Intent(LastVisitDetails.this, StoreSelection.class);
				
				//Location_Getting_Service.closeFlag = 0;
				prevP2.putExtra("imei", imei);
				prevP2.putExtra("userDate", date);
				prevP2.putExtra("pickerDate", pickerDate);
				prevP2.putExtra("rID", rID);
				startActivity(prevP2);
				finish();
				
				
				
			}
		});
		
		nxtP4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0)
			{
				
				if (validate())
				{
					getStoreVisitCode();
					 String flgGSTCompliance="NA";
						// String flgGSTCapture="NA";
						 String GSTNumber="NA";


					String passdLevel = battLevel + "%";
					dbengine.UpdateStoreVisitBattVisitWise(storeID,passdLevel,StoreVisitCode);

						 if(ll_gstDetails.getVisibility()==View.VISIBLE)
						 {
							 flgGSTCapture="1";
						 }
						 else if(ll_gstDetails.getVisibility()==View.GONE)
						 {
							 flgGSTCapture="0";
						 }
						 
						 if(rb_gst_yes.isChecked())
						 {
							 flgGSTCompliance="1";
							 if(!edit_gstYes.getText().toString().trim().equals(null) ||!edit_gstYes.getText().toString().trim().equals(""))
							 {
								 GSTNumber=edit_gstYes.getText().toString().trim();
							 }
						 }
						 else if(rb_gst_no.isChecked())
						 {
							 flgGSTCompliance="0"; 
						 }
						 else if(rb_pending.isChecked())
						 {
							 flgGSTCompliance="2";
						 }
						dbengine.UpdateStoreInfoGST(storeID,flgGSTCapture,flgGSTCompliance,GSTNumber);
						
						long syncTIMESTAMP = System.currentTimeMillis();
						Date dateobjNew = new Date(syncTIMESTAMP);
						SimpleDateFormat dfnew = new SimpleDateFormat(
								"dd-MM-yyyy HH:mm:ss",Locale.ENGLISH);
						String startTS = dfnew.format(dateobjNew);
						
						
						long StartClickTime = System.currentTimeMillis();
						Date dateobj1 = new Date(StartClickTime);
						SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss",Locale.ENGLISH);
						String StartClickTimeFinal = df1.format(dateobj1);
						
						
						CommonInfo.fileContent=CommonInfo.fileContent+"     "+imei+"_"+storeID+"_"+"Next Button Click on last Visit Details"+StartClickTimeFinal;
						
						
						
						dbengine.open();
						dbengine.UpdateStoreEndVisit(storeID,startTS);
						dbengine.close();
						Intent nxtP4 = new Intent(LastVisitDetails.this,ProductOrderEntry.class);
						//Intent nxtP4 = new Intent(LastVisitDetails.this,ProductOrderFilterSearch_RecycleView.class);
						nxtP4.putExtra("storeID", storeID);
						nxtP4.putExtra("SN", selStoreName);
						nxtP4.putExtra("imei", imei);
						nxtP4.putExtra("userdate", date);
						nxtP4.putExtra("pickerDate", pickerDate);
						startActivity(nxtP4);
						finish();
				}
				
			}
		});
		
		
	    
	     quotationBtn.setOnClickListener(new OnClickListener()
	         {
		
				@Override
				public void onClick(View arg0)
				{
					Intent intents=new Intent(LastVisitDetails.this,QuotationActivity.class);
					intents.putExtra("quatationFlag", "NEW");  //Update
					intents.putExtra("SalesQuoteId", "Null");
					intents.putExtra("storeID", storeID);
					intents.putExtra("SN", selStoreName);
					intents.putExtra("imei", imei);
					intents.putExtra("userdate", date);
					intents.putExtra("pickerDate", pickerDate); 
					intents.putExtra("prcID", "NULL"); 
					CommonInfo.SalesQuoteId="BLANK";
					CommonInfo.prcID="NULL";
					CommonInfo.newQuottionID="NULL";
					CommonInfo.globalValueOfPaymentStage="0"+"_"+"0"+"_"+"0";

					
					startActivity(intents);
					finish();
			
		      }
	     });
	     
	       // setQuotationList();
	        paymentSectionInitialize();
	        
	        
	        
	        ll_gstDetails=(LinearLayout) findViewById(R.id.ll_gstDetails);
			 ll_gstDependent=(LinearLayout) findViewById(R.id.ll_gstDependent);
			 edit_gstYes=(EditText) findViewById(R.id.edit_gstYes);
			 gst_Details_but=(Button) findViewById(R.id.gst_Details_but);
			 
			 rb_gst_yes=(RadioButton) findViewById(R.id.rb_gst_yes);
			 rb_gst_no=(RadioButton) findViewById(R.id.rb_gst_no);
		     rb_pending=(RadioButton) findViewById(R.id.rb_pending);
			 RadioBtnGSTFunctionality();
		btn_CloseStore= (Button) findViewById(R.id.btn_CloseStore);

		int checkVisitType=dbengine.checkVisitTypeStatus(storeID,StoreVisitCode);
		if(checkVisitType==0)
		{
			btn_CloseStore.setVisibility(View.VISIBLE);
		}
		else
		{
			btn_CloseStore.setVisibility(View.GONE);
		}

		btn_CloseStore.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v)
			{

				String[] arrayReasonDesc=new String[hmapReasonForNoSales.size()];
				int index=0;
				for(Map.Entry<String,String> listArrayRsn:hmapReasonForNoSales.entrySet())
				{
					arrayReasonDesc[index]=listArrayRsn.getKey();
					index++;
				}
				customAlertNoInvoice(arrayReasonDesc);

				/*Intent nxtP4=new Intent(LastVisitDetails.this,StoreClosedActivity.class);
				nxtP4.putExtra("storeID", storeID);
				nxtP4.putExtra("SN", selStoreName);
				nxtP4.putExtra("imei", imei);
				nxtP4.putExtra("userdate", date);
				nxtP4.putExtra("pickerDate", pickerDate);
				startActivity(nxtP4);
				finish();*/
			}
		});
		tv_outstandingvalue=(TextView) findViewById(R.id.tv_outstandingvalue);
		 outstandingvalue=dbengine.fnGetStoretblLastOutstanding(storeID);
		tv_outstandingvalue.setText(""+outstandingvalue);
		setInvoiceData();


		//getStoreVisitCode();

	}


	
	private boolean validate()
	{ 
		 if(flgGSTCapture.equals("1") && rb_gst_no.isChecked()== false && rb_gst_yes.isChecked()== false && rb_pending.isChecked()== false)
		 {
			 showAlertSingleButtonError(LastVisitDetails.this.getResources().getString(R.string.SelectGst));
			  return false;
		 }
		else if(rb_gst_yes.isChecked()== true && edit_gstYes.getText().toString().trim().equals(null))
		{
			showAlertSingleButtonError(LastVisitDetails.this.getResources().getString(R.string.FillGst));
			  return false;
		}
		else if(rb_gst_yes.isChecked()== true && edit_gstYes.getText().toString().trim().equals("NA"))
		{
			showAlertSingleButtonError(LastVisitDetails.this.getResources().getString(R.string.FillGst));
			  return false;
		}
		else if(rb_gst_yes.isChecked()== true && edit_gstYes.getText().toString().trim().equals("0"))
		{
			showAlertSingleButtonError(LastVisitDetails.this.getResources().getString(R.string.FillGst));
			  return false;
		}
		else if(rb_gst_yes.isChecked()== true && edit_gstYes.getText().toString().trim().equals(""))
		{
			showAlertSingleButtonError(LastVisitDetails.this.getResources().getString(R.string.FillGst));
			  return false;
		}
	
		else
		{
			
			
			return true;
		}
		
		
	}
	
	public void RadioBtnGSTFunctionality()
	{
		
		gst_Details_but.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				
				
				//flgGSTCompliance="1";
				 if(flgGSTCompliance.equals("1"))
				 {
					 final AlertDialog builder = new AlertDialog.Builder(LastVisitDetails.this).create();
				       

						LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				        View openDialog = inflater.inflate(R.layout.custom_dialog_with_edittext, null);
				        openDialog.setBackgroundColor(Color.parseColor("#ffffff"));
				        
				        builder.setCancelable(false);
				     	TextView header_text=(TextView)openDialog. findViewById(R.id.txt_header);
				     	final TextView msg=(TextView)openDialog. findViewById(R.id.msg);
				     	
						final Button ok_but=(Button)openDialog. findViewById(R.id.but_yes);
						final Button cancel=(Button)openDialog. findViewById(R.id.but_no);
						
						String GSTNUmber="";
						if(TextUtils.isEmpty(edit_gstYes.getText()))
						{
							GSTNUmber="";
						}
						else
						{
							GSTNUmber=edit_gstYes.getText().toString().trim();
						}

						final EditText editTextmobile=(EditText)openDialog. findViewById(R.id.editTextmobile);
						editTextmobile.setText(GSTNUmber);
						editTextmobile.setEnabled(true);
						
						if(!TextUtils.isEmpty(edit_gstYes.getText()))
						 {
							 int pos = edit_gstYes.getText().length();
							 editTextmobile.setSelection(pos);
						 }
						
						
						//cancel.setVisibility(View.GONE);
				     	header_text.setText(getText(R.string.AlertDialogHeaderMsg));
				     	msg.setText(getText(R.string.EnterGst));
				     	
				     	ok_but.setText(getText(R.string.Confirm));
				     	cancel.setText(getText(R.string.AlertDialogCancelButton));
				     	
						builder.setView(openDialog,0,0,0,0);

				        ok_but.setOnClickListener(new OnClickListener() 
				        {
							
							@Override
							public void onClick(View arg0) 
							{
								
								if(ok_but.getText().toString().trim().equals("Confirm"))
								{
									if(TextUtils.isEmpty(editTextmobile.getText()))
									{
										msg.setText(getText(R.string.PleaseEnterGstToProc));
										cancel.setVisibility(View.GONE);
										editTextmobile.setVisibility(View.GONE);
										ok_but.setText(getText(R.string.AlertDialogOkButton));
										
									}
									
									else
									{
									// TODO Auto-generated method stub
										if(!TextUtils.isEmpty(editTextmobile.getText()))
										{
											edit_gstYes.setText(editTextmobile.getText());
										}
										builder.dismiss();
									}
								}
								else
								{
									msg.setText(getText(R.string.EnterGst));
									cancel.setVisibility(View.VISIBLE);
									editTextmobile.setVisibility(View.VISIBLE);
									editTextmobile.setEnabled(true);
									ok_but.setText(getText(R.string.Confirm));
								}
							}
						});
				        cancel.setOnClickListener(new OnClickListener() 
				        {
							
							@Override
							public void onClick(View arg0) 
							{
								// TODO Auto-generated method stub
								//builder.dismiss();
								
								builder.dismiss();
								   // editTextmobile.setEnabled(true);
									//editTextmobile.setFocusable(true);
									if(!TextUtils.isEmpty(editTextmobile.getText()))
									{
										edit_gstYes.setText(editTextmobile.getText());
									}
									
									
									
									/* if(!TextUtils.isEmpty(editTextmobile.getText()))
									 {
										 int pos = editTextmobile.getText().length();
										 editTextmobile.setSelection(pos);
									 }*/
								
								
							}
						});
				        
				        builder.setIcon(R.drawable.info_icon);
				      
				 
				     	builder.show();
				 }
				
			}
		});
		
		GstComplianceData=dbengine.getGstDataByStore(storeID);
		
		flgGSTCapture=GstComplianceData.get(0);
		flgGSTCompliance=GstComplianceData.get(1);
		String GSTNumber=GstComplianceData.get(2);
		GSTNumberGlobal=GSTNumber;
		
		int flgServerRecord=dbengine.fnGetflgServerRecordFromOutletMstr(storeID);
		
		
		if(flgGSTCapture.equals("1")) 
		  {
			 //set Visibility gone for Godrej acc. to Avinash Sir
			//ll_gstDetails.setVisibility(View.VISIBLE);
			  ll_gstDetails.setVisibility(View.GONE);

			     if(flgGSTCompliance.equals("0"))
			     {
				      rb_gst_no.setEnabled(true);
				      rb_gst_yes.setEnabled(true);
					 rb_pending.setEnabled(true);
				      rb_gst_no.setChecked(true);
				      ll_gstDependent.setVisibility(View.GONE);
				      edit_gstYes.setText("");
			     }
			     else if(flgGSTCompliance.equals("1"))
			     {
				      rb_gst_yes.setChecked(true);
				      ll_gstDependent.setVisibility(View.VISIBLE);
				      if(GSTNumber.equals("NA") || GSTNumber.equals("0") || GSTNumber.trim().equals(""))
						{
				    	  edit_gstYes.setText("");
						}
				        else
				        {
				         edit_gstYes.setText(GSTNumber);
				        }
				     // edit_gstYes.setEnabled(true);
				      if(flgServerRecord==0)
				      {
					      rb_gst_no.setEnabled(true);
					      rb_gst_yes.setEnabled(true);
						  rb_pending.setEnabled(true);
					      edit_gstYes.setEnabled(true);
					      gst_Details_but.setVisibility(View.GONE);
				      }
				      else
				      {
				       rb_gst_no.setEnabled(false);
				       rb_gst_yes.setEnabled(false);
						  rb_pending.setEnabled(false);
				       edit_gstYes.setEnabled(false);
				       gst_Details_but.setVisibility(View.VISIBLE);
				       if(TextUtils.isEmpty(edit_gstYes.getText()))
					     {
				    	   gst_Details_but.setVisibility(View.GONE);
				    	   edit_gstYes.setEnabled(true);
				    	   TextView gst_compliance_txt=(TextView)findViewById(R.id.gst_compliance_txt);
				    	   gst_compliance_txt.setVisibility(View.VISIBLE);
				    	   rb_gst_yes.setVisibility(View.VISIBLE);
				    	   rb_gst_no.setVisibility(View.VISIBLE);
							 rb_pending.setVisibility(View.VISIBLE);
					     }
				       else
				       {
				    	   TextView gst_compliance_txt=(TextView)findViewById(R.id.gst_compliance_txt);
				    	   gst_compliance_txt.setVisibility(View.GONE);
				    	   rb_gst_yes.setVisibility(View.GONE);
				    	   rb_gst_no.setVisibility(View.GONE);
						   rb_pending.setVisibility(View.GONE);
				       }
				      }
			    
			      
			     }
				 else if(flgGSTCompliance.equals("2"))
				 {
					 rb_gst_no.setEnabled(true);
					 rb_gst_yes.setEnabled(true);
					 rb_pending.setEnabled(true);
					 rb_pending.setChecked(true);
					 ll_gstDependent.setVisibility(View.GONE);
					 edit_gstYes.setText("");
				 }
			  }
		else if(flgGSTCapture.equals("0"))
		{
			ll_gstDetails.setVisibility(View.GONE);
			edit_gstYes.setText("");
			rb_gst_no.setChecked(false);
			rb_gst_yes.setChecked(false);
			rb_pending.setChecked(false);
			
		}
		
		
		rb_gst_no.setOnClickListener(new OnClickListener()
		{	
			@Override
			public void onClick(View v) 
			{
				if(rb_gst_no.isChecked())
				{
					flgGSTCompliance="0";
					rb_gst_no.setChecked(true);
					rb_gst_yes.setChecked(false);
					rb_pending.setChecked(false);
					ll_gstDependent.setVisibility(View.GONE);
					edit_gstYes.setText("");
					GSTNumberGlobal="0";
						
				}
			}
		});

		rb_pending.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if(rb_pending.isChecked())
				{
					flgGSTCompliance="2";
					rb_gst_no.setChecked(false);
					rb_gst_yes.setChecked(false);
					rb_pending.setChecked(true);
					ll_gstDependent.setVisibility(View.GONE);
					edit_gstYes.setText("");
					GSTNumberGlobal="0";

				}
			}
		});
		
		rb_gst_yes.setOnClickListener(new OnClickListener()
		{	
			@Override
			public void onClick(View v) 
			{
				if(rb_gst_yes.isChecked())
				{
					flgGSTCompliance="1";
					rb_gst_yes.setChecked(true);
					rb_gst_no.setChecked(false);
					rb_pending.setChecked(false);
					ll_gstDependent.setVisibility(View.VISIBLE);
					
					
					if(!TextUtils.isEmpty(GSTNumberGlobal))
				     {
						/*if(!GSTNumberGlobal.equals("NA") || !GSTNumberGlobal.equals("0"))
						{
							edit_gstYes.setText(GSTNumberGlobal);
						}*/
						
						 if(GSTNumberGlobal.equals("NA") || GSTNumberGlobal.equals("0"))
							{
					    	  edit_gstYes.setText("");
							}
					        else
					        {
					         edit_gstYes.setText(GSTNumberGlobal);
					        }
				     }
					
					 if(TextUtils.isEmpty(edit_gstYes.getText()))
				     {
			    	   gst_Details_but.setVisibility(View.GONE);
			    	   edit_gstYes.setEnabled(true);
				     }
					
					
				}
			}
		});
	}
	
	
	public void paymentSectionInitialize() {}
	 public void disableAllCheckBoxOfPaymentMode(LinearLayout parentofPaymentModeCheckboxes) {
			// TODO Auto-generated method stub 
			 int count = parentofPaymentModeCheckboxes.getChildCount();
				
			 for (int ui=0;ui<count;ui++) 
			 {
				 View ch = parentofPaymentModeCheckboxes.getChildAt(ui);
		            if (ch instanceof CheckBox)
		            {
		            	ch.setEnabled(false);
		            	
		            }
			 }

		}
	public void fillValuesInPaymentSection(String allValuesOfPaymentStageID) {}
	
	public void checkBoxCreationwhenPageLoading(String paymentStageID) {}
	
	 public void CustomAlertBoxForSchemeDetails(String TagValue)
	 {
		// dbengine.open();
		 String SchemeId=(TagValue.split(Pattern.quote("_")))[0];
		 String SchemeDesc=(TagValue.split(Pattern.quote("_")))[1];
		 
		  AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
         
		  ScrollView scroll = new ScrollView(this);
		 
		  scroll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		  
		
	        LinearLayout layout = new LinearLayout(this);
	        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
	        layout.setOrientation(LinearLayout.VERTICAL);
	        layout.setLayoutParams(parms);
            layout.setGravity(Gravity.CLIP_VERTICAL);
	        layout.setPadding(2,2,2,0);
	        layout.setBackgroundColor(Color.WHITE);
	        
	       

	        TextView tv = new TextView(this);
	        tv.setText(getText(R.string.genTermInformation));
	        tv.setPadding(40, 10, 40, 10);
	        tv.setBackgroundColor(Color.parseColor("#486FA8"));
	        tv.setGravity(Gravity.CENTER);
	        tv.setTextSize(20);
	        tv.setTextColor(Color.parseColor("#ffffff"));

	       
	        LinearLayout.LayoutParams tv1Params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
	        tv1Params.bottomMargin = 5;
	        
	        
	      
	        
	        
	        for(int i=0;i<1;i++)
		        {
		        	
		        	LinearLayout ChildViewDynamic = new LinearLayout(LastVisitDetails.this);
			    	ChildViewDynamic.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,25f));
			    	ChildViewDynamic.setOrientation(LinearLayout.VERTICAL);
			    	ChildViewDynamic.setWeightSum(25f);
			    	
			    	 TextView tv1 = new TextView(this);
				     tv1.setTextColor(Color.BLACK);
				     tv1.setBackgroundColor(Color.parseColor("#FFFEFC"));
				     SchemeDesc=SchemeDesc;
				   //tv1.setText("Scheme Name :"+SchemeDesc);
				     tv1.setTextColor(Color.parseColor("#303F9F"));
				     tv1.setTypeface(null, Typeface.BOLD);
				     String mystring="Scheme Name :"+SchemeDesc;
				     SpannableString content = new SpannableString(mystring);
				     content.setSpan(new UnderlineSpan(), 0, mystring.length(), 0);
				     tv1.setText(content);
				     
				     ChildViewDynamic.addView(tv1,tv1Params);
				    
				     
				     
				     String AllSchemeSlabID[]=dbengine.fnGetAllSchSlabbasedOnSchemeID(SchemeId);
				    
				     // below two line for Testing,  please comment below two line for live
				    // hmapSchemeSlabIdSlabDes.put("62", "Buy [ 12 units from (Series - [ Go Fresh Cream, Go UHT Milk, Gowardhan Milk rich, Gowardhan Skim Milk Powder, GO Badam Milk, GO Butter Milk, Gowardhan Butter) AND 1 lines among these (Series - [ Go UHT Milk, GO Butter Milk) AND Rs 2000 Total Net value ] OR [5 Kg volume of Products from (Series - [ Go UHT Milk, GO Butter Milk) AND Rs 2000 value of Products from (Series - [ Go UHT Milk, GO Butter Milk)]");
				    // hmapSchemeSlabIdSlabDes.put("63", "Buy [ 12 units from (Series - [ Go Fresh Cream, Go UHT Milk, Gowardhan Milk rich, Gowardhan Skim Milk Powder, GO Badam Milk, GO Butter Milk, Gowardhan Butter) AND 1 lines among these (Series - [ Go UHT Milk, GO Butter Milk) AND Rs 2000 Total Net value ] OR [5 Kg volume of Products from (Series - [ Go UHT Milk, GO Butter Milk) AND Rs 2000 value of Products from (Series - [ Go UHT Milk, GO Butter Milk)]");
				     
				     
				    // hmapSchmeSlabIdSlabDes
				     
				    // hmapSchemeSlabIdBenifitDes.put("62", "GET [ 2 units from Same Product - [Exceptions -  3 units for Old Buyer] AND  Coupon No. 0002 AND 2% discount on Invoice value  - [Exceptions -  3% for Old Buyer, 4% for New Buyer]]");
				    // hmapSchemeSlabIdBenifitDes.put("63", "GET [ 2 units from Same Product - [Exceptions -  3 units for Old Buyer] AND  Coupon No. 0002 AND 2% discount on Invoice value  - [Exceptions -  3% for Old Buyer, 4% for New Buyer]]");
					    
				     int k=0;
				   for(int j=0;j<AllSchemeSlabID.length;j++)   // change 3 into SchmSlabId.length which i got hmapSchmSlabIdSchmID (Length of SchmSlabId)
				   {
					   
					    k=j+1;
					    
					   // System.out.println("List of all SchemeSlabID :"+AllSchemeSlabID[j]);
					   
					    TextView tv2 = new TextView(this);
					    tv2.setTextColor(Color.BLACK);
					    tv2.setBackgroundColor(Color.parseColor("#FFFEFC"));
					    String aa[]=dbengine.fnGetAllSchSlabDescbasedOnSchemeSlabID(AllSchemeSlabID[j]);
					    tv2.setText("Slab "+k+"  :"+aa[0]); // It is for Live 
					  //  tv2.setText("Slab "+k+"  :"+hmapSchemeSlabIdSlabDes.get("62"));  // It is for Testing
					    tv2.setTextColor(Color.parseColor("#E65100"));
					    
					    ChildViewDynamic.addView(tv2,tv1Params);
					    
					   
					    
					    TextView tv3 = new TextView(this);
					    tv3.setTextColor(Color.BLACK);
					    tv3.setBackgroundColor(Color.parseColor("#FFFEFC"));
					    String bb[]=dbengine.fnGetAllBenifitDescrbasedOnSchemeSlabID(AllSchemeSlabID[j]);
					    tv3.setText("Benifit :"+bb[0]);  // It is for Live 
					   // tv3.setText("Benifit :"+hmapSchemeSlabIdBenifitDes.get("62"));   // It is for Testing
					    tv3.setTextColor(Color.parseColor("#3BA1B3"));
					    
					   
					    ChildViewDynamic.addView(tv3,tv1Params);
					    
					  
					    
				   }
		        
		           
			    
			    
		           layout.addView(ChildViewDynamic,tv1Params);
		        }
	        
	        scroll.addView(layout);
	       
	       
	        
	       
	      
	        alertDialogBuilder.setView(scroll);
	        alertDialogBuilder.setCustomTitle(tv);
            alertDialogBuilder.setIcon(R.drawable.info_ico);
	        alertDialogBuilder.setCancelable(false);

	      

	        // Setting Positive "Yes" Button
	        alertDialogBuilder.setPositiveButton(getText(R.string.AlertDialogOkButton), new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	            	 dialog.cancel();
	            	// dbengine.close();
	              }
	        });

	        AlertDialog alertDialog = alertDialogBuilder.create();
	       

	        try {
	            alertDialog.show();
	        } catch (Exception e) {
	            // WindowManager$BadTokenException will be caught and the app would
	            // not display the 'Force Close' message
	            e.printStackTrace();
	        }
	 
		 
      
		
	 }
	 
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		

		
		this.unregisterReceiver(this.mBatInfoReceiver);
	}


	
	
public void setInvoiceData(){

	strInvoiceData=dbengine.fetch_Store_tblInvoiceLastVisitDetails(storeID);

	String val[]=new String[strInvoiceData.length];

	if(strInvoiceData.length>0)
	{
		for(int i=0;i<strInvoiceData.length;i++){
			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = inflater.inflate(R.layout.inflate_row_invoice_lastvisit, null);

			TextView InvCode= (TextView) view.findViewById(R.id.InvCode);
			TextView InvDate= (TextView) view.findViewById(R.id.InvDate);
			TextView OutStandingAmnt= (TextView) view.findViewById(R.id.OutStandingAmnt);
			TextView AmntOverDue= (TextView) view.findViewById(R.id.AmntOverDue);


			StringTokenizer tokens = new StringTokenizer(String.valueOf(strInvoiceData[i]), "^");


			String strInvCode=tokens.nextToken().toString().trim();
			String strInvDate=tokens.nextToken().toString().trim();
			String strOutstandingAmt=tokens.nextToken().toString().trim();
			String strAmtOverdue=tokens.nextToken().toString().trim();


			InvCode.setText(strInvCode);
			InvDate.setText(strInvDate);
			OutStandingAmnt.setText(strOutstandingAmt);
			AmntOverDue.setText(strAmtOverdue);


			ll_inflateInvoiceData.addView(view);

		}

	}
	else {
		ll_InvoiceLastVisit.setVisibility(View.GONE);
	}
}

	public String genStoreVisitCode()
	{
		long syncTIMESTAMP = System.currentTimeMillis();
		Date dateobj = new Date(syncTIMESTAMP);
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss",Locale.ENGLISH);
		String VisitStartTS = df.format(dateobj);
		String cxz;
		cxz = UUID.randomUUID().toString();


		StringTokenizer tokens = new StringTokenizer(String.valueOf(cxz), "-");

		String val1 = tokens.nextToken().trim();
		String val2 = tokens.nextToken().trim();
		String val3 = tokens.nextToken().trim();
		String val4 = tokens.nextToken().trim();
		cxz = tokens.nextToken().trim();

		String IMEIid =  CommonInfo.imei.substring(9);
		cxz = "StoreVisitCode" + "-" +IMEIid +"-"+cxz+"-"+VisitStartTS.replace(" ", "").replace(":", "").trim();


		return cxz;

	}


	public void customAlertNoInvoice(final String[] list)
	{

		final Dialog listDialog = new Dialog(LastVisitDetails.this);
		listDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		listDialog.setContentView(R.layout.no_invoice_layout);
		listDialog.setCanceledOnTouchOutside(false);
		listDialog.setCancelable(false);
		WindowManager.LayoutParams parms = listDialog.getWindow().getAttributes();
		parms.gravity =Gravity.CENTER;
		parms.width = WindowManager.LayoutParams.FILL_PARENT;
        //there are a lot of settings, for dialog, check them all out!
		parms.dimAmount = (float) 0.5;




		final Button btn_sbmt=(Button) listDialog.findViewById(R.id.btn_sbmt);


		Button btn_cancel=(Button) listDialog.findViewById(R.id.btn_cancel);
		RadioGroup rg_Yes_No= (RadioGroup) listDialog.findViewById(R.id.rg_Yes_No);
		final Spinner spnr_rsn= (Spinner) listDialog.findViewById(R.id.spnr_rsn);
		ArrayAdapter adapter=new ArrayAdapter(LastVisitDetails.this,R.layout.initial_spinner_text,list);
		adapter.setDropDownViewResource(R.layout.spina);
		spnr_rsn.setAdapter(adapter);
		final LinearLayout ll_other_reason= (LinearLayout) listDialog.findViewById(R.id.ll_other_reason);
		ll_other_reason.setVisibility(View.INVISIBLE);
		//    TextView txtVwSubmit=(TextView) listDialog.findViewById(R.id.txtVwSubmit);

		rg_Yes_No.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				btn_sbmt.setEnabled(true);
				btn_sbmt.setBackgroundResource(R.drawable.custom_button);
				if(checkedId==R.id.rb_SC_Yes)
				{
					isStrCls=true;
					ll_other_reason.setVisibility(View.INVISIBLE);


				}
				else if(checkedId==R.id.rb_SC_No)
				{
					isStrCls=false;
					ll_other_reason.setVisibility(View.VISIBLE);


				}
			}
		});
		btn_sbmt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getStoreVisitCode();
				TmpInvoiceCodePDA=genTempInvoiceCodePDA();

				if(isStrCls)
				{



					String passdLevel = battLevel + "%";
					dbengine.UpdateStoreVisitBattVisitWise(storeID,passdLevel,StoreVisitCode);
					dbengine.fnUpdateflgVisitNoInvoiceClick(storeID,StoreVisitCode,1,0);
					listDialog.dismiss();

					Intent nxtP4=new Intent(LastVisitDetails.this,StoreClosedActivity.class);
					nxtP4.putExtra("storeID", storeID);
					nxtP4.putExtra("SN", selStoreName);
					nxtP4.putExtra("imei", imei);
					nxtP4.putExtra("userdate", date);
					nxtP4.putExtra("pickerDate", pickerDate);
					startActivity(nxtP4);
					finish();
				}
				else
				{
					if((!spnr_rsn.getSelectedItem().toString().equals("Select Reason")) && (!spnr_rsn.getSelectedItem().toString().equals("No Reason")))
					{
						dbengine.fnUpdateflgVisitNoInvoiceClick(storeID,StoreVisitCode,1,Integer.parseInt(hmapReasonForNoSales.get(spnr_rsn.getSelectedItem().toString())));
						if(outstandingvalue>0)
						{

							listDialog.dismiss();
							Intent AmtCollectIntent = new Intent(LastVisitDetails.this, CollectionActivityNew.class);   //
							AmtCollectIntent.putExtra("storeID", storeID);
							AmtCollectIntent.putExtra("imei", imei);
							AmtCollectIntent.putExtra("userdate", date);
							AmtCollectIntent.putExtra("pickerDate", pickerDate);
							AmtCollectIntent.putExtra("SN", selStoreName);
							AmtCollectIntent.putExtra("OrderPDAID", TmpInvoiceCodePDA);
							AmtCollectIntent.putExtra("intentFrom", "LastVisitDetails");
							startActivity(AmtCollectIntent);
							finish();
						}
						else
						{
							listDialog.dismiss();

                                      /* FullSyncDataNow task = new FullSyncDataNow(CollectionActivityNew.this);
                                       task.execute();*/



							dbengine.open();
							if ((dbengine.PrevLocChk(storeID.trim(),StoreVisitCode)) )
							{
								dbengine.close();

								FullSyncDataNow task = new FullSyncDataNow(LastVisitDetails.this);
								task.execute();
							}
							else
							{

								dbengine.close();
								boolean isGPSEnabled2 = false;
								boolean isNetworkEnabled2=false;
								isGPSEnabled2 = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
								isNetworkEnabled2 = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

								if(!isGPSEnabled2)
								{
									isGPSEnabled2 = false;
								}
								if(!isNetworkEnabled2)
								{
									isNetworkEnabled2 = false;
								}
								if(!isGPSEnabled2 && !isNetworkEnabled2)
								{
									try
									{
										showSettingsAlert();
									}
									catch(Exception e)
									{

									}

									isGPSEnabled2 = false;
									isNetworkEnabled2=false;
								}
								else
								{
									appLocationService=new AppLocationService();

									/* pm = (PowerManager) getSystemService(POWER_SERVICE);
									 *//*  wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK
							                | PowerManager.ACQUIRE_CAUSES_WAKEUP
							                | PowerManager.ON_AFTER_RELEASE, "INFO");
							        wl.acquire();*/


									pDialog2STANDBY= ProgressDialog.show(LastVisitDetails.this,getText(R.string.genTermPleaseWaitNew) ,getText(R.string.genTermRetrivingLocation), true);
									pDialog2STANDBY.setIndeterminate(true);

									pDialog2STANDBY.setCancelable(false);
									pDialog2STANDBY.show();

									if(isGooglePlayServicesAvailable()) {
										createLocationRequest();

										mGoogleApiClient = new GoogleApiClient.Builder(LastVisitDetails.this)
												.addApi(LocationServices.API)
												.addConnectionCallbacks(LastVisitDetails.this)
												.addOnConnectionFailedListener(LastVisitDetails.this)
												.build();
										mGoogleApiClient.connect();
									}
									//startService(new Intent(DynamicActivity.this, AppLocationService.class));
									startService(new Intent(LastVisitDetails.this, AppLocationService.class));
									Location nwLocation=appLocationService.getLocation(locationManager,LocationManager.GPS_PROVIDER,location);
									Location gpsLocation=appLocationService.getLocation(locationManager,LocationManager.NETWORK_PROVIDER,location);
									countDownTimer2 = new CoundownClass2(startTime, interval);
									countDownTimer2.start();



								}


							}
						}
					}
					else
					{
						Toast.makeText(LastVisitDetails.this, "Please select reason for No Invoice.", Toast.LENGTH_SHORT).show();
					}


				}
			}
		});
		btn_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				listDialog.dismiss();
			}
		});




		//now that the dialog is set up, it's time to show it
		listDialog.show();

	}

	public String genTempInvoiceCodePDA()
	{
		//store ID generation <x>
		long syncTIMESTAMP = System.currentTimeMillis();
		Date dateobj = new Date(syncTIMESTAMP);
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss",Locale.ENGLISH);
		String VisitStartTS = df.format(dateobj);
		String cxz;
		cxz = UUID.randomUUID().toString();
		/*cxz.split("^([^-]*,[^-]*,[^-]*,[^-]*),(.*)$");*/

		StringTokenizer tokens = new StringTokenizer(String.valueOf(cxz), "-");

		String val1 = tokens.nextToken().trim();
		String val2 = tokens.nextToken().trim();
		String val3 = tokens.nextToken().trim();
		String val4 = tokens.nextToken().trim();
		cxz = tokens.nextToken().trim();



						/*TelephonyManager tManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
						String imei = tManager.getDeviceId();*/
		String IMEIid =  CommonInfo.imei.substring(9);
		//cxz = IMEIid +"-"+cxz;
		cxz = "TmpInvoiceCodePDA" + "-" +IMEIid +"-"+cxz+"-"+VisitStartTS.replace(" ", "").replace(":", "").trim();


		return cxz;
		//-_
	}

	public class CoundownClass2 extends CountDownTimer {

		public CoundownClass2(long startTime, long interval) {
			super(startTime, interval);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onTick(long millisUntilFinished) {

			if(FusedLocationAccuracy!=null){
				if(Double.parseDouble(FusedLocationAccuracy)<20 && (!FusedLocationAccuracy.equals("0"))){


					countDownTimer2.onFinish();
					countDownTimer2.cancel();

				}
			}

		}

		@Override
		public void onFinish() {

			isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			String GpsLat="0";
			String GpsLong="0";
			String GpsAccuracy="0";
			String GpsAddress="0";
			if(isGPSEnabled)
			{

				Location nwLocation=appLocationService.getLocation(locationManager,LocationManager.GPS_PROVIDER,location);
				if(nwLocation!=null){
					double lattitude=nwLocation.getLatitude();
					double longitude=nwLocation.getLongitude();
					double accuracy= nwLocation.getAccuracy();
					GpsLat=""+lattitude;
					GpsLong=""+longitude;
					GpsAccuracy=""+accuracy;

					GPSLocationLatitude=""+lattitude;
					GPSLocationLongitude=""+longitude;
					GPSLocationProvider="GPS";
					GPSLocationAccuracy=""+accuracy;
					System.out.println("LOCATION(GPS)  LATTITUDE: " +lattitude + "LONGITUDE:" + longitude+ "accuracy:" + accuracy);
					//text2.setText(" LOCATION(GPS) \n LATTITUDE: " +lattitude + "\nLONGITUDE:" + longitude+ "\naccuracy:" + accuracy);
					//Toast.makeText(getApplicationContext(), " LOCATION(NW) \n LATTITUDE: " +lattitude + "\nLONGITUDE:" + longitude+ "\naccuracy:" + accuracy, Toast.LENGTH_LONG).show();
				}
			}

			Location gpsLocation=appLocationService.getLocation(locationManager,LocationManager.NETWORK_PROVIDER,location);
			String NetwLat="0";
			String NetwLong="0";
			String NetwAccuracy="0";
			String NetwAddress="0";
			if(gpsLocation!=null){
				double lattitude1=gpsLocation.getLatitude();
				double longitude1=gpsLocation.getLongitude();
				double accuracy1= gpsLocation.getAccuracy();
				NetwLat=""+lattitude1;
				NetwLong=""+longitude1;
				NetwAccuracy=""+accuracy1;

				NetworkLocationLatitude=""+lattitude1;
				NetworkLocationLongitude=""+longitude1;
				NetworkLocationProvider="Network";
				NetworkLocationAccuracy=""+accuracy1;
				System.out.println("LOCATION(N/W)  LATTITUDE: " +lattitude1 + "LONGITUDE:" + longitude1+ "accuracy:" + accuracy1);
				// Toast.makeText(this, " LOCATION(NW) \n LATTITUDE: " +lattitude + "\nLONGITUDE:" + longitude, Toast.LENGTH_LONG).show();
				//text1.setText(" LOCATION(N/W) \n LATTITUDE: " +lattitude1 + "\nLONGITUDE:" + longitude1+ "\naccuracy:" + accuracy1);

			}
					 /* TextView accurcy=(TextView) findViewById(R.id.Acuracy);
					  accurcy.setText("GPS:"+GPSLocationAccuracy+"\n"+"NETWORK"+NetworkLocationAccuracy+"\n"+"FUSED"+fusedData);*/

			System.out.println("LOCATION Fused"+fusedData);
			String FusedLat="0";
			String FusedLong="0";
			String FusedAccuracy="0";
			String FusedAddress="0";

			if(!FusedLocationProvider.equals(""))
			{
				fnAccurateProvider="Fused";
				fnLati=FusedLocationLatitude;
				fnLongi=FusedLocationLongitude;
				fnAccuracy= Double.parseDouble(FusedLocationAccuracy);

				FusedLat=FusedLocationLatitude;
				FusedLong=FusedLocationLongitude;
				FusedAccuracy=FusedLocationAccuracy;
			}




			appLocationService.KillServiceLoc(appLocationService,locationManager);
			try {
				if(mGoogleApiClient!=null && mGoogleApiClient.isConnected())
				{
					stopLocationUpdates();
					mGoogleApiClient.disconnect();
				}
			}
			catch (Exception e){

			}




			fnAccurateProvider="";
			fnLati="0";
			fnLongi="0";
			fnAccuracy=0.0;

			if(!FusedLocationProvider.equals(""))
			{
				fnAccurateProvider="Fused";
				fnLati=FusedLocationLatitude;
				fnLongi=FusedLocationLongitude;
				fnAccuracy= Double.parseDouble(FusedLocationAccuracy);
			}

			if(!fnAccurateProvider.equals(""))
			{
				if(!GPSLocationProvider.equals(""))
				{
					if(Double.parseDouble(GPSLocationAccuracy)<=fnAccuracy)
					{
						fnAccurateProvider="Gps";
						fnLati=GPSLocationLatitude;
						fnLongi=GPSLocationLongitude;
						fnAccuracy= Double.parseDouble(GPSLocationAccuracy);
					}
				}
			}
			else
			{
				if(!GPSLocationProvider.equals(""))
				{
					fnAccurateProvider="Gps";
					fnLati=GPSLocationLatitude;
					fnLongi=GPSLocationLongitude;
					fnAccuracy= Double.parseDouble(GPSLocationAccuracy);
				}
			}

			if(!fnAccurateProvider.equals(""))
			{
				if(!NetworkLocationProvider.equals(""))
				{
					if(Double.parseDouble(NetworkLocationAccuracy)<=fnAccuracy)
					{
						fnAccurateProvider="Network";
						fnLati=NetworkLocationLatitude;
						fnLongi=NetworkLocationLongitude;
						fnAccuracy= Double.parseDouble(NetworkLocationAccuracy);
					}
				}
			}
			else
			{
				if(!NetworkLocationProvider.equals(""))
				{
					fnAccurateProvider="Network";
					fnLati=NetworkLocationLatitude;
					fnLongi=NetworkLocationLongitude;
					fnAccuracy= Double.parseDouble(NetworkLocationAccuracy);
				}
			}
			// fnAccurateProvider="";
			if(fnAccurateProvider.equals(""))
			{
				if(pDialog2STANDBY.isShowing())
				{
					pDialog2STANDBY.dismiss();
				}
				//alert ... try again nothing found // return back

				// Toast.makeText(getApplicationContext(), "Please try again, No Fused,GPS or Network found.", Toast.LENGTH_LONG).show();

				showAlertForEveryOne(LastVisitDetails.this.getResources().getString(R.string.AlertTryAgain));
			}
			else
			{


				if(pDialog2STANDBY.isShowing())
				{
					pDialog2STANDBY.dismiss();
				}
				if(!GpsLat.equals("0") )
				{
					fnCreateLastKnownGPSLoction(GpsLat,GpsLong,GpsAccuracy);
				}

				if(!checkLastFinalLoctionIsRepeated(String.valueOf(fnLati), String.valueOf(fnLongi), String.valueOf(fnAccuracy)))
				{

                    fnCreateLastKnownFinalLocation(String.valueOf(fnLati), String.valueOf(fnLongi), String.valueOf(fnAccuracy));
                    UpdateLocationAndProductAllData();
				}
				else
				{
				    countSubmitClicked++;
					if(countSubmitClicked==1)
					{
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(LastVisitDetails.this);

						// Setting Dialog Title
						alertDialog.setTitle(getText(R.string.genTermNoDataConnection));
						alertDialog.setIcon(R.drawable.error_info_ico);
						alertDialog.setCancelable(false);
						// Setting Dialog Message
						alertDialog.setMessage(LastVisitDetails.this.getResources().getString(R.string.AlertSameLoc));

						// On pressing Settings button
						alertDialog.setPositiveButton(LastVisitDetails.this.getResources().getString(R.string.AlertDialogOkButton), new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								countSubmitClicked++;
								Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
								startActivity(intent);
							}
						});

						// Showing Alert Message
						alertDialog.show();



					}
					else
					{
						UpdateLocationAndProductAllData();
					}


				}

			}

		}

	}
	public void showAlertForEveryOne(String msg)
	{
		AlertDialog.Builder alertDialogNoConn = new AlertDialog.Builder(LastVisitDetails.this);
		alertDialogNoConn.setTitle(getText(R.string.genTermNoDataConnection));
		alertDialogNoConn.setMessage(msg);
		alertDialogNoConn.setCancelable(false);
		alertDialogNoConn.setNeutralButton(LastVisitDetails.this.getResources().getString(R.string.AlertDialogOkButton),new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
				dialog.dismiss();
				FusedLocationLatitude="0";
				FusedLocationLongitude="0";
				FusedLocationProvider="0";
				FusedLocationAccuracy="0";

				GPSLocationLatitude="0";
				GPSLocationLongitude="0";
				GPSLocationProvider="0";
				GPSLocationAccuracy="0";

				NetworkLocationLatitude="0";
				NetworkLocationLongitude="0";
				NetworkLocationProvider="0";
				NetworkLocationAccuracy="0";


				String GpsLat="0";
				String GpsLong="0";
				String GpsAccuracy="0";
				String GpsAddress="0";
				String NetwLat="0";
				String  NetwLong="0";
				String NetwAccuracy="0";
				String NetwAddress="0";
				String  FusedLat="0";
				String FusedLong="0";
				String FusedAccuracy="0";
				String FusedAddress="0";
				checkHighAccuracyLocationMode(LastVisitDetails.this);
				dbengine.open();
				dbengine.UpdateStoreActualLatLongi(storeID,String.valueOf(fnLati), String.valueOf(fnLongi), "" + fnAccuracy,fnAccurateProvider,flgLocationServicesOnOffOrderReview,flgGPSOnOffOrderReview,flgNetworkOnOffOrderReview,flgFusedOnOffOrderReview,flgInternetOnOffWhileLocationTrackingOrderReview,flgRestartOrderReview,flgStoreOrderOrderReview,StoreVisitCode,VisitTimeInSideStore);



					butClickForGPS=0;
					try
					{
						FullSyncDataNow task = new FullSyncDataNow(LastVisitDetails.this);
						task.execute();
					}
					catch (Exception e) {
						// TODO Autouuid-generated catch block
						e.printStackTrace();
						//System.out.println("onGetStoresForDayCLICK: Exec(). EX: "+e);
					}

				}


		});
		alertDialogNoConn.setIcon(R.drawable.info_ico);
		AlertDialog alert = alertDialogNoConn.create();
		alert.show();
	}

	public void checkHighAccuracyLocationMode(Context context) {
		int locationMode = 0;
		String locationProviders;

		flgLocationServicesOnOffOrderReview=0;
		flgGPSOnOffOrderReview=0;
		flgNetworkOnOffOrderReview=0;
		flgFusedOnOffOrderReview=0;
		flgInternetOnOffWhileLocationTrackingOrderReview=0;

		if(isGooglePlayServicesAvailable())
		{
			flgFusedOnOffOrderReview=1;
		}
		if(isOnline())
		{
			flgInternetOnOffWhileLocationTrackingOrderReview=1;
		}

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
		{
			//Equal or higher than API 19/KitKat
			try {
				locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
				if (locationMode == Settings.Secure.LOCATION_MODE_HIGH_ACCURACY){
					flgLocationServicesOnOffOrderReview=1;
					flgGPSOnOffOrderReview=1;
					flgNetworkOnOffOrderReview=1;
					//flgFusedOnOff=1;
				}
				if (locationMode == Settings.Secure.LOCATION_MODE_BATTERY_SAVING){
					flgLocationServicesOnOffOrderReview=1;
					flgGPSOnOffOrderReview=0;
					flgNetworkOnOffOrderReview=1;
					// flgFusedOnOff=1;
				}
				if (locationMode == Settings.Secure.LOCATION_MODE_SENSORS_ONLY){
					flgLocationServicesOnOffOrderReview=1;
					flgGPSOnOffOrderReview=1;
					flgNetworkOnOffOrderReview=0;
					//flgFusedOnOff=0;
				}
			} catch (Settings.SettingNotFoundException e) {
				e.printStackTrace();
			}
		}
		else {
			//Lower than API 19
			locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);


			if (TextUtils.isEmpty(locationProviders)) {
				locationMode = Settings.Secure.LOCATION_MODE_OFF;

				flgLocationServicesOnOffOrderReview = 0;
				flgGPSOnOffOrderReview = 0;
				flgNetworkOnOffOrderReview = 0;
				// flgFusedOnOff = 0;
			}
			if (locationProviders.contains(LocationManager.GPS_PROVIDER) && locationProviders.contains(LocationManager.NETWORK_PROVIDER)) {
				flgLocationServicesOnOffOrderReview = 1;
				flgGPSOnOffOrderReview = 1;
				flgNetworkOnOffOrderReview = 1;
				//flgFusedOnOff = 0;
			} else {
				if (locationProviders.contains(LocationManager.GPS_PROVIDER)) {
					flgLocationServicesOnOffOrderReview = 1;
					flgGPSOnOffOrderReview = 1;
					flgNetworkOnOffOrderReview = 0;
					// flgFusedOnOff = 0;
				}
				if (locationProviders.contains(LocationManager.NETWORK_PROVIDER)) {
					flgLocationServicesOnOffOrderReview = 1;
					flgGPSOnOffOrderReview = 0;
					flgNetworkOnOffOrderReview = 1;
					//flgFusedOnOff = 0;
				}
			}
		}

	}


	private boolean isGooglePlayServicesAvailable() {
		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if (ConnectionResult.SUCCESS == status) {
			return true;
		} else {
			GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
			return false;
		}
	}
	protected void createLocationRequest() {
		mLocationRequest = new LocationRequest();
		mLocationRequest.setInterval(INTERVAL);
		mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
		mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
	}
	@Override
	public void onConnectionFailed(ConnectionResult arg0)
	{
		// TODO Auto-generated method stub
		LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, appLocationService);
	}

	@Override
	public void onConnected(Bundle arg0)
	{
		// TODO Auto-generated method stub
		LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, appLocationService);

		startLocationUpdates();
	}


	protected void startLocationUpdates()
	{
		PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

	}

	@Override
	public void onConnectionSuspended(int arg0)
	{
		// TODO Auto-generated method stub
		LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, appLocationService);

	}

	protected void stopLocationUpdates() {
		LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);

	}

	private void updateUI() {
		Location loc =mCurrentLocation;
		if (null != mCurrentLocation) {
			String lat = String.valueOf(mCurrentLocation.getLatitude());
			String lng = String.valueOf(mCurrentLocation.getLongitude());

			FusedLocationLatitude=lat;
			FusedLocationLongitude=lng;
			FusedLocationProvider=mCurrentLocation.getProvider();
			FusedLocationAccuracy=""+mCurrentLocation.getAccuracy();
			fusedData="At Time: " + mLastUpdateTime  +
					"Latitude: " + lat  +
					"Longitude: " + lng  +
					"Accuracy: " + mCurrentLocation.getAccuracy() +
					"Provider: " + mCurrentLocation.getProvider();

		} else {

		}
	}

	public void fnCreateLastKnownGPSLoction(String chekLastGPSLat,String chekLastGPSLong,String chekLastGpsAccuracy)
	{

		try {

			JSONArray jArray=new JSONArray();
			JSONObject jsonObjMain=new JSONObject();


			JSONObject jOnew = new JSONObject();
			jOnew.put( "chekLastGPSLat",chekLastGPSLat);
			jOnew.put( "chekLastGPSLong",chekLastGPSLong);
			jOnew.put( "chekLastGpsAccuracy", chekLastGpsAccuracy);


			jArray.put(jOnew);
			jsonObjMain.put("GPSLastLocationDetils", jArray);

			File jsonTxtFolder = new File(Environment.getExternalStorageDirectory(), CommonInfo.AppLatLngJsonFile);
			if (!jsonTxtFolder.exists())
			{
				jsonTxtFolder.mkdirs();

			}
			String txtFileNamenew="GPSLastLocation.txt";
			File file = new File(jsonTxtFolder,txtFileNamenew);
			String fpath = Environment.getExternalStorageDirectory()+"/"+CommonInfo.AppLatLngJsonFile+"/"+txtFileNamenew;


			// If file does not exists, then create it
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}


			FileWriter fw;
			try {
				fw = new FileWriter(file.getAbsoluteFile());

				BufferedWriter bw = new BufferedWriter(fw);

				bw.write(jsonObjMain.toString());

				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				 /*  file=contextcopy.getFilesDir();
				//fileOutputStream=contextcopy.openFileOutput("GPSLastLocation.txt", Context.MODE_PRIVATE);
				fileOutputStream.write(jsonObjMain.toString().getBytes());
				fileOutputStream.close();*/
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{

		}
	}
	public boolean checkLastFinalLoctionIsRepeated(String currentLat,String currentLong,String currentAccuracy){
		boolean repeatedLoction=false;

		try {

			String chekLastGPSLat="0";
			String chekLastGPSLong="0";
			String chekLastGpsAccuracy="0";
			File jsonTxtFolder = new File(Environment.getExternalStorageDirectory(), CommonInfo.FinalLatLngJsonFile);
			if (!jsonTxtFolder.exists())
			{
				jsonTxtFolder.mkdirs();

			}
			String txtFileNamenew="FinalGPSLastLocation.txt";
			File file = new File(jsonTxtFolder,txtFileNamenew);
			String fpath = Environment.getExternalStorageDirectory()+"/"+CommonInfo.FinalLatLngJsonFile+"/"+txtFileNamenew;

			// If file does not exists, then create it
			if (file.exists()) {
				StringBuffer buffer=new StringBuffer();
				String myjson_stampiGPSLastLocation="";
				StringBuffer sb = new StringBuffer();
				BufferedReader br = null;

				try {
					br = new BufferedReader(new FileReader(file));

					String temp;
					while ((temp = br.readLine()) != null)
						sb.append(temp);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						br.close(); // stop reading
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				myjson_stampiGPSLastLocation=sb.toString();

				JSONObject jsonObjGPSLast = new JSONObject(myjson_stampiGPSLastLocation);
				JSONArray jsonObjGPSLastInneralues = jsonObjGPSLast.getJSONArray("GPSLastLocationDetils");

				String StringjsonGPSLastnew = jsonObjGPSLastInneralues.getString(0);
				JSONObject jsonObjGPSLastnewwewe = new JSONObject(StringjsonGPSLastnew);

				chekLastGPSLat=jsonObjGPSLastnewwewe.getString("chekLastGPSLat");
				chekLastGPSLong=jsonObjGPSLastnewwewe.getString("chekLastGPSLong");
				chekLastGpsAccuracy=jsonObjGPSLastnewwewe.getString("chekLastGpsAccuracy");

				if(currentLat!=null )
				{
					if(currentLat.equals(chekLastGPSLat) && currentLong.equals(chekLastGPSLong) && currentAccuracy.equals(chekLastGpsAccuracy))
					{
						repeatedLoction=true;
					}
				}
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return repeatedLoction;

	}
	public void fnCreateLastKnownFinalLocation(String chekLastGPSLat,String chekLastGPSLong,String chekLastGpsAccuracy)
	{

		try {

			JSONArray jArray=new JSONArray();
			JSONObject jsonObjMain=new JSONObject();


			JSONObject jOnew = new JSONObject();
			jOnew.put( "chekLastGPSLat",chekLastGPSLat);
			jOnew.put( "chekLastGPSLong",chekLastGPSLong);
			jOnew.put( "chekLastGpsAccuracy", chekLastGpsAccuracy);


			jArray.put(jOnew);
			jsonObjMain.put("GPSLastLocationDetils", jArray);

			File jsonTxtFolder = new File(Environment.getExternalStorageDirectory(), CommonInfo.FinalLatLngJsonFile);
			if (!jsonTxtFolder.exists())
			{
				jsonTxtFolder.mkdirs();

			}
			String txtFileNamenew="FinalGPSLastLocation.txt";
			File file = new File(jsonTxtFolder,txtFileNamenew);
			String fpath = Environment.getExternalStorageDirectory()+"/"+CommonInfo.FinalLatLngJsonFile+"/"+txtFileNamenew;


			// If file does not exists, then create it
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}


			FileWriter fw;
			try {
				fw = new FileWriter(file.getAbsoluteFile());

				BufferedWriter bw = new BufferedWriter(fw);

				bw.write(jsonObjMain.toString());

				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				 /*  file=contextcopy.getFilesDir();
				//fileOutputStream=contextcopy.openFileOutput("FinalGPSLastLocation.txt", Context.MODE_PRIVATE);
				fileOutputStream.write(jsonObjMain.toString().getBytes());
				fileOutputStream.close();*/
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{

		}
	}

	@Override
	public void onLocationChanged(Location args0) {
		// TODO Auto-generated method stub
		mCurrentLocation = args0;
		mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
		updateUI();

	}

	private class FullSyncDataNow extends AsyncTask<Void, Void, Void> {


		ProgressDialog pDialogGetStores;
		public FullSyncDataNow(LastVisitDetails activity)
		{
			pDialogGetStores = new ProgressDialog(activity);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();


			pDialogGetStores.setTitle(getText(R.string.genTermPleaseWaitNew));
			pDialogGetStores.setMessage("Submitting no invoice reason");
			pDialogGetStores.setIndeterminate(false);
			pDialogGetStores.setCancelable(false);
			pDialogGetStores.setCanceledOnTouchOutside(false);
			pDialogGetStores.show();


		}

		@Override

		protected Void doInBackground(Void... params) {

			int Outstat=1;


			//InvoiceTableDataDeleteAndSaving(Outstat,flgTransferStatus);
			//TransactionTableDataDeleteAndSaving(Outstat);
			Double cntInvoceValue=dbengine.fetch_Store_InvValAmount(storeID,TmpInvoiceCodePDA);
			cntInvoceValue=Double.parseDouble(new DecimalFormat("##.##").format(cntInvoceValue));

			Outstat=3;




			dbengine.UpdateStoreVisitWiseTables(storeID, 3,StoreVisitCode,TmpInvoiceCodePDA);
			long  syncTIMESTAMP = System.currentTimeMillis();
			Date dateobj = new Date(syncTIMESTAMP);
			SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss",Locale.ENGLISH);
			String StampEndsTime = df.format(dateobj);


			dbengine.open();
			dbengine.UpdateStoreEndVisit(storeID, StampEndsTime);
			dbengine.UpdateStoreProductAppliedSchemesBenifitsRecords(storeID.trim(),"3",TmpInvoiceCodePDA,TmpInvoiceCodePDA);
			dbengine.UpdateStoreStoreReturnDetail(storeID.trim(),"3",TmpInvoiceCodePDA,TmpInvoiceCodePDA);
			dbengine.UpdateStoreFlag(storeID.trim(), 3);
			dbengine.UpdateStoreOtherMainTablesFlag(storeID.trim(), 3,TmpInvoiceCodePDA,TmpInvoiceCodePDA);




			//dbengine.UpdateStoreReturnphotoFlag(storeID.trim(), 5);

			dbengine.close();





			dbengine.updateStoreQuoteSubmitFlgInStoreMstr(storeID.trim(),0,StoreVisitCode);


			dbengine.open();
			String presentRoute=dbengine.GetActiveRouteID();
			dbengine.close();


			/*long syncTIMESTAMP = System.currentTimeMillis();
			Date dateobj = new Date(syncTIMESTAMP);*/
			SimpleDateFormat df1 = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss",Locale.ENGLISH);

			newfullFileName=imei+"."+presentRoute+"."+df1.format(dateobj);




			try {


				File OrderXMLFolder = new File(Environment.getExternalStorageDirectory(), CommonInfo.OrderXMLFolder);

				if (!OrderXMLFolder.exists())
				{
					OrderXMLFolder.mkdirs();

				}
				String routeID=dbengine.GetActiveRouteIDSunil();

				DA.open();
				DA.export(CommonInfo.DATABASE_NAME, newfullFileName,routeID);
				DA.close();

				dbengine.savetbl_XMLfiles(newfullFileName, "3","1");

				dbengine.UpdateXMLCreatedFilesTablesFlag(5);
			} catch (Exception e) {

				e.printStackTrace();
				if(pDialogGetStores.isShowing())
				{
					pDialogGetStores.dismiss();
				}
			}
			return null;
		}
		@Override
		protected void onCancelled() {

		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if(pDialogGetStores.isShowing())
			{
				pDialogGetStores.dismiss();
			}
			try
			{
				StoreSelection.flgChangeRouteOrDayEnd=0;
				StoreSelection.flgChangeRouteOrDayEnd=4;
				Intent syncIntent = new Intent(LastVisitDetails.this, SyncMaster.class);
				//syncIntent.putExtra("xmlPathForSync", Environment.getExternalStorageDirectory() + "/RSPLSFAXml/" + newfullFileName + ".xml");
				syncIntent.putExtra("xmlPathForSync", Environment.getExternalStorageDirectory() + "/" + CommonInfo.OrderXMLFolder + "/" + newfullFileName + ".xml");
				syncIntent.putExtra("OrigZipFileName", newfullFileName);
				syncIntent.putExtra("whereTo", "Regular");
				startActivity(syncIntent);
				finish();
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
	}

	public void UpdateLocationAndProductAllData()
	{
		checkHighAccuracyLocationMode(LastVisitDetails.this);
		dbengine.open();
		dbengine.UpdateStoreActualLatLongi(storeID,String.valueOf(fnLati), String.valueOf(fnLongi), "" + fnAccuracy,fnAccurateProvider,flgLocationServicesOnOffOrderReview,flgGPSOnOffOrderReview,flgNetworkOnOffOrderReview,flgFusedOnOffOrderReview,flgInternetOnOffWhileLocationTrackingOrderReview,flgRestartOrderReview,flgStoreOrderOrderReview,StoreVisitCode,VisitTimeInSideStore);


		dbengine.close();


			try
			{
				FullSyncDataNow task = new FullSyncDataNow(LastVisitDetails.this);
				task.execute();
			}
			catch (Exception e) {
				// TODO Autouuid-generated catch block
				e.printStackTrace();
				//System.out.println("onGetStoresForDayCLICK: Exec(). EX: "+e);
			}


	}

}
