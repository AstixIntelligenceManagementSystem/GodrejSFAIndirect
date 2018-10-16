package project.astix.com.godrejsfaindirect;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class InvoiceCollectionInfoAdapter extends RecyclerView.Adapter<InvoiceCollectionInfoAdapter.InvClctnHolder> {

    Context mContext;
    LinkedHashMap<String,ArrayList<String>> hmapStoreInvoice;
    public InvoiceCollectionInfoAdapter(Context mContext, LinkedHashMap<String,ArrayList<String>> hmapStoreInvoice)
    {
        this.hmapStoreInvoice=hmapStoreInvoice;
        this.mContext=mContext;


    }
    @Override
    public InvClctnHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.store_report_info,parent,false);



        return new InvClctnHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InvClctnHolder holder, int position) {

        inflateStoreInfo(holder);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class InvClctnHolder extends RecyclerView.ViewHolder
    {
       public  LinearLayout ll_storeInvClctnInfo;
        public InvClctnHolder(View itemView) {
            super(itemView);
            ll_storeInvClctnInfo= (LinearLayout) itemView.findViewById(R.id.ll_storeInvClctnInfo);


        }
    }

            public void inflateStoreInfo(InvClctnHolder holder)
            {

                if(hmapStoreInvoice!=null && hmapStoreInvoice.size()>0)
                {
                    TableLayout tblLayout=new TableLayout(mContext);
                    tblLayout.setLayoutParams(new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT));
                    for(Map.Entry<String,ArrayList<String>> entryInvoice:hmapStoreInvoice.entrySet())
                    {

                        TableLayout tblLayoutInner=new TableLayout(mContext);
                        tblLayoutInner.setLayoutParams(new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT));
                        TextView txtStrName=getTextView(entryInvoice.getKey().split(Pattern.quote("^"))[1],true);


                        ArrayList<String> listStrInvcDtls=entryInvoice.getValue();
                        if(listStrInvcDtls!=null && listStrInvcDtls.size()>0)
                        {

                            for(String invoiceInfo:listStrInvcDtls)
                            {
                                TextView txtInvoiceNum,txtInvoiceVal,txtPrdctCount,txtCllctnVal;
                                if(!invoiceInfo.split(Pattern.quote("^"))[0].equals("NA"))
                                {
                                    txtInvoiceNum=getTextView(invoiceInfo.split(Pattern.quote("^"))[0],false);
                                }
                                else
                                {
                                    txtInvoiceNum=getTextView("",false);
                                }
                                if(!invoiceInfo.split(Pattern.quote("^"))[1].equals("NA"))
                                {
                                    txtInvoiceVal=getTextView(invoiceInfo.split(Pattern.quote("^"))[1],false);
                                }
                                else
                                {
                                    txtInvoiceVal=getTextView("",false);
                                }
                                if(!invoiceInfo.split(Pattern.quote("^"))[2].equals("NA"))
                                {
                                    // txtPrdctCount=getTextView(invoiceInfo.split(Pattern.quote("^"))[2],false);
                                    txtPrdctCount=getTextView(invoiceInfo.split(Pattern.quote("^"))[2],false);
                                }
                                else
                                {
                                    txtPrdctCount=getTextView("",false);
                                }
                                if(!invoiceInfo.split(Pattern.quote("^"))[3].equals("NA"))
                                {
                                    txtCllctnVal=getTextView(invoiceInfo.split(Pattern.quote("^"))[3],false);
                                }
                                else
                                {
                                    txtCllctnVal=getTextView("",false);
                                }



                               TableRow tblRowInfo=getTableRowInfo(txtInvoiceNum,txtInvoiceVal,txtPrdctCount,txtPrdctCount);

                                tblLayoutInner.addView(tblRowInfo);
                            }

                        }

                        TableRow rowFinally=getTableRow(txtStrName,tblLayoutInner);
                        tblLayout.addView(rowFinally);

                    }
                    holder.ll_storeInvClctnInfo.addView(tblLayout);
                }







            }



    public TextView getTextView(String desc,boolean isHeader)
    {


        TextView txtVw_ques=new TextView(mContext);
        if(isHeader)
        {
            LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(0, WindowManager.LayoutParams.MATCH_PARENT, 1f);
            txtVw_ques.setLayoutParams(layoutParams1);

        }
        else
        {
            LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(0, WindowManager.LayoutParams.WRAP_CONTENT, 1f);
            txtVw_ques.setLayoutParams(layoutParams1);

        }


        txtVw_ques.setGravity(Gravity.CENTER);
        if(isHeader)
        {
            txtVw_ques.setTextColor(mContext.getResources().getColor(R.color.black));
        }
        else
        {
            txtVw_ques.setTextColor(mContext.getResources().getColor(R.color.blue));
        }
        txtVw_ques.setBackground(mContext.getResources().getDrawable(R.drawable.table_cell_bg));
        txtVw_ques.setText(desc);


        return txtVw_ques;
    }

    private TableRow getTableRow(TextView strName, TableLayout tblRow) {
        TableRow lay = new TableRow(mContext);

        lay.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, 1f);
        strName.setLayoutParams(layoutParams1);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, 4f);
        tblRow.setLayoutParams(layoutParams2);


        lay.addView(strName);
        lay.addView(tblRow);


        return lay;

    }
    private TableRow getTableRowInfo( TextView invcNum,TextView invVal,TextView prdctQty,TextView collctn) {
        TableRow lay = new TableRow(mContext);

        lay.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, 1f);
        invcNum.setLayoutParams(layoutParams1);

        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, 1f);
        //layoutParams2.setMargins(10, 0, 0, 0);
        invVal.setLayoutParams(layoutParams3);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, 1f);
        prdctQty.setLayoutParams(layoutParams4);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, 1f);
        collctn.setLayoutParams(layoutParams5);

        lay.addView(invcNum);
        lay.addView(invcNum);
        lay.addView(prdctQty);
        lay.addView(collctn);


        return lay;

    }

}
