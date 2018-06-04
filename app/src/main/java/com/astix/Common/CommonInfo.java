package com.astix.Common;

import android.net.Uri;

import java.io.File;

public class CommonInfo
{


	// Its for Live Path on 194 Server




	public static final String DistributorMapXMLFolder="GodrejDistributorMapXML";
	public static final String DistributorStockXMLFolder="GodrejDistributorStockXML";
	public static final String DistributorCheckInXMLFolder="GodrejDistributorCheckInXML";
	public static final String Preference="GodrejPrefrence";
	public static int flgAllRoutesData=1;
	public static File imageF_savedInstance=null;
	public static String imageName_savedInstance=null;
	public static String clickedTagPhoto_savedInstance=null;
	public static Uri uriSavedImage_savedInstance=null;
	public static String imei="";
	public static String SalesQuoteId="BLANK";
	public static String quatationFlag="";
	public static String fileContent="";
	public static String prcID="NULL";
	public static String newQuottionID="NULL";
	public static String globalValueOfPaymentStage="0"+"_"+"0"+"_"+"0";
	public static String WebServicePath="http://103.20.212.194/WebServiceAndroidGodrejLive/Service.asmx";
	public static String VersionDownloadPath="http://103.20.212.194/downloads/";
	public static String VersionDownloadAPKName="GodrejSFA.apk";
	public static String DATABASE_NAME = "DbGodrejSFAApp";
	public static int AnyVisit = 0;
	public static int DATABASE_VERSIONID = 1;      // put this field value based on value in table on the server
	public static String AppVersionID = "1.0";   // put this field value based on value in table on the server
	public static int Application_TypeID = 2; //1=Parag Store Mapping,2=Parag SFA Indirect,3=Parag SFA Direct
	public static String OrderSyncPath="http://103.20.212.194/ReadXML_GodrejLive/DefaultSFA.aspx";
	public static String ImageSyncPath="http://103.20.212.194/ReadXML_GodrejImagesLive/Default.aspx";
	public static String OrderTextSyncPath="http://103.20.212.194/ReadTxtFileForGodrejSFALive/default.aspx";
	public static String OrderSyncPathDistributorMap="http://103.20.212.194/ReadXML_GodrejLive/DefaultSODistributorMapping.aspx";
	public static String InvoiceSyncPath="http://103.20.212.194/ReadXML_GodrejInvoiceLive/Default.aspx";
	public static String DistributorSyncPath="http://103.20.212.194/ReadXML_GodrejSFADistributionLive/Default.aspx";
	public static String WebStockOutUrl="http://103.20.212.194/godrejsfa/manageorder/frmStockTransferToVanDetail_PDA.aspx";
	public static String WebStockInUrl="http://103.20.212.194/godrejsfa/manageorder/frmstockin.aspx";
	public static String OrderXMLFolder="GodrejSFAXml";
	public static String ImagesFolder="GodrejSFAImages";
	public static String TextFileFolder="GodrejTextFile";
	public static String InvoiceXMLFolder="GodrejInvoiceXml";
	public static String FinalLatLngJsonFile="GodrejSFAFinalLatLngJson";
	public static String AppLatLngJsonFile="GodrejSFALatLngJson";
	public static int DistanceRange=3000;
	public static String SalesPersonTodaysTargetMsg="";
	public static int CoverageAreaNodeID=0;
	public static int CoverageAreaNodeType=0;
	public static int SalesmanNodeId=0;
	public static int SalesmanNodeType=0;
	public static int flgDataScope=0;
	public static int FlgDSRSO=0;
	public static String ActiveRouteSM="0";



	// Its for Test Path on 194 Server


/*

	public static int flgAllRoutesData=1;
	public static File imageF_savedInstance=null;
	public static String imageName_savedInstance=null;
	public static String clickedTagPhoto_savedInstance=null;
	public static Uri uriSavedImage_savedInstance=null;

	public static String imei="";
	public static String SalesQuoteId="BLANK";
	public static String quatationFlag="";
	public static String fileContent="";
	public static String prcID="NULL";

	public static String newQuottionID="NULL";
	public static String globalValueOfPaymentStage="0"+"_"+"0"+"_"+"0";

	public static String WebStockOutUrl="http://103.20.212.194/godrejsfa_staging/manageorder/frmStockTransferToVanDetail_PDA.aspx";
	public static String WebStockInUrl="http://103.20.212.194/godrejsfa_staging/manageorder/frmstockin.aspx";

	public static String WebServicePath="http://103.20.212.194/WebServiceAndroidGodrejTest/Service.asmx";
	public static String VersionDownloadPath="http://103.20.212.194/downloads/";
	public static String VersionDownloadAPKName="GodrejSFATest.apk";

	public static String DATABASE_NAME = "DbGodrejSFAApp";
	public static int AnyVisit = 0;

	public static int DATABASE_VERSIONID = 24;      // put this field value based on value in table on the server
	public static String AppVersionID = "1.17";  	// put this field value based on value in table on the server
	public static int Application_TypeID = 2; 		//1=Parag Store Mapping,2=Parag SFA Indirect,3=Parag SFA Direct

	public static String OrderSyncPath="http://103.20.212.194/ReadXML_GodrejTest/DefaultSFA.aspx";
	public static String ImageSyncPath="http://103.20.212.194/ReadXML_GodrejImagesTest/Default.aspx";

	public static String OrderTextSyncPath="http://103.20.212.194/ReadTxtFileForGodrejSFALive/default.aspx";

	public static String OrderSyncPathDistributorMap="http://103.20.212.194/ReadXML_GodrejTest/DefaultSODistributorMapping.aspx";

	public static String InvoiceSyncPath="http://103.20.212.194/ReadXML_GodrejInvoiceTest/Default.aspx";

	public static String DistributorSyncPath="http://103.20.212.194/ReadXML_GodrejSFADistributionTest/Default.aspx";

	public static String OrderXMLFolder="GodrejSFAXml";
	public static String ImagesFolder="GodrejSFAImages";
	public static String TextFileFolder="GodrejTextFile";
	public static String InvoiceXMLFolder="GodrejInvoiceXml";
	public static String FinalLatLngJsonFile="GodrejSFAFinalLatLngJson";

	public static final String DistributorMapXMLFolder="GodrejDistributorMapXML";
	public static final String DistributorStockXMLFolder="GodrejDistributorStockXML";
	public static final String DistributorCheckInXMLFolder="GodrejDistributorCheckInXML";

	public static String AppLatLngJsonFile="GodrejSFALatLngJson";

	public static int DistanceRange=3000;
	public static String SalesPersonTodaysTargetMsg="";
	public static final String Preference="GodrejPrefrence";



	public static int CoverageAreaNodeID=0;
	public static int CoverageAreaNodeType=0;
	public static int SalesmanNodeId=0;
	public static int SalesmanNodeType=0;
	public static int flgDataScope=0;
	public static int FlgDSRSO=0;
	public static String ActiveRouteSM="0";



*/



	// Its for Dev Path on 194 Server


/*

	    public static final String DistributorMapXMLFolder="GodrejDistributorMapXML";
	    public static final String DistributorStockXMLFolder="GodrejDistributorStockXML";
	    public static final String DistributorCheckInXMLFolder="GodrejDistributorCheckInXML";
	    public static final String Preference="GodrejPrefrence";
        public static int flgAllRoutesData=1;
	    public static File imageF_savedInstance=null;
	    public static String imageName_savedInstance=null;
	    public static String clickedTagPhoto_savedInstance=null;
	    public static Uri uriSavedImage_savedInstance=null;
	    public static String imei="";
	    public static String SalesQuoteId="BLANK";
	    public static String quatationFlag="";
	    public static String fileContent="";
	    public static String prcID="NULL";
	    public static String newQuottionID="NULL";
	    public static String globalValueOfPaymentStage="0"+"_"+"0"+"_"+"0";
	    public static String WebServicePath="http://103.20.212.194/WebServiceAndroidGodrejDevelopment/Service.asmx";
	    public static String VersionDownloadPath="http://103.20.212.194/downloads/";
		public static String VersionDownloadAPKName="GodrejSFADev.apk";
		public static String DATABASE_NAME = "DbGodrejSFAApp";
		public static int AnyVisit = 0;
		public static int DATABASE_VERSIONID = 28;      // put this field value based on value in table on the server
		public static String AppVersionID = "1.22";   // put this field value based on value in table on the server
		public static int Application_TypeID = 2; //1=Parag Store Mapping,2=Parag SFA Indirect,3=Parag SFA Direct
	    public static String OrderSyncPath="http://103.20.212.194/ReadXML_GodrejDevelopment/DefaultSFA.aspx";
	    public static String ImageSyncPath="http://103.20.212.194/ReadXML_GodrejImagesDevelopment/Default.aspx";
	    public static String OrderTextSyncPath="http://103.20.212.194/ReadTxtFileForGodrejSFADevelopment/default.aspx";
	    public static String OrderSyncPathDistributorMap="http://103.20.212.194/ReadXML_GodrejTest/DefaultSODistributorMapping.aspx";
	    public static String InvoiceSyncPath="http://103.20.212.194/ReadXML_GodrejInvoiceDevelopment/Default.aspx";
	    public static String DistributorSyncPath="http://103.20.212.194/ReadXML_GodrejSFADistributionDevelopment/Default.aspx";
		public static String WebStockOutUrl="http://103.20.212.194/godrejsfa_staging/manageorder/frmStockTransferToVanDetail_PDA.aspx";
		public static String WebStockInUrl="http://103.20.212.194/godrejsfa_staging/manageorder/frmstockin.aspx";
		public static String OrderXMLFolder="GodrejSFAXml";
		public static String ImagesFolder="GodrejSFAImages";
	    public static String TextFileFolder="GodrejTextFile";
	    public static String InvoiceXMLFolder="GodrejInvoiceXml";
		public static String FinalLatLngJsonFile="GodrejSFAFinalLatLngJson";
		public static String AppLatLngJsonFile="GodrejSFALatLngJson";
		public static int DistanceRange=3000;
	    public static String SalesPersonTodaysTargetMsg="";
		public static int CoverageAreaNodeID=0;
		public static int CoverageAreaNodeType=0;
		public static int SalesmanNodeId=0;
		public static int SalesmanNodeType=0;
		public static int flgDataScope=0;
		public static int FlgDSRSO=0;
	    public static String ActiveRouteSM="0";
*/



}
