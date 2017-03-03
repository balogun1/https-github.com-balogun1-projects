package com.example.balogunal_amin.rrsfeeds;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.w3c.dom.Document;
import   org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by balogunal-amin on 2016-04-02.
 */

public class ReadRss extends AsyncTask<Void,Void,Void>{
    /**
     * Creates a new asynchronous task. This
     * constructor must be invoked on the UI thread.
     */
    ArrayList<FeedItem>feedItems;
    RecyclerView recylerView;

    Context context;
    ProgressDialog dialog;
    String address= "http://www.sciencemag.org/rss/news_current.xml";
    URL url;
    public ReadRss(Context context, RecyclerView recylerView) {
        super();
        this.context = context;
        this.recylerView = recylerView;
        dialog = new ProgressDialog(context);
        dialog.setMessage("Loading...");
    }

    /**
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.</p>
     * <p/>
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @param aVoid The result of the operation computed by {@link #doInBackground}.
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        dialog.dismiss();
        MyAdapter adapter = new MyAdapter(context,feedItems);
        //MyAdapter adapter1 = new MyAdapter(context, feedItems2);
        recylerView.setLayoutManager(new LinearLayoutManager(context));
        recylerView.addItemDecoration(new VerticalSpace(50));
        recylerView.setAdapter(adapter);

    }

    /**
     * Runs on the UI thread before {@link #doInBackground}.
     *
     * @see #onPostExecute
     * @see #doInBackground
     */
    @Override
    protected void onPreExecute() {
        dialog.show();
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        ProcessXmlBackground(getData());
        return null;
    }

    private void ProcessXmlBackground(Document data) {
        feedItems = new ArrayList<FeedItem>();
        if( data != null){
          Element root = data.getDocumentElement();
            Node channel = root.getChildNodes().item(1);
            NodeList items = channel.getChildNodes();
            for(int i = 0; i<items.getLength(); i++){
                Node currentChild = items.item(i);
                if(currentChild.getNodeName().equalsIgnoreCase("item")){
                     FeedItem item = new FeedItem();
                    NodeList itemChilds =currentChild.getChildNodes();
                    for(int j = 0; j<itemChilds.getLength(); j++){
                        Node current = itemChilds.item(j);
                        if(current.getNodeName().equalsIgnoreCase("title")){
                         item.setTitle(current.getTextContent());
                        }else if(current.getNodeName().equalsIgnoreCase("link")){
                        item.setLink(current.getTextContent());

                        }else if(current.getNodeName().equalsIgnoreCase("description")){
                          item.setDescription(current.getTextContent());
                        }else if(current.getNodeName().equalsIgnoreCase("pubDate")){
                            item.setPubDate(current.getTextContent());
                        }else if(current.getNodeName().equalsIgnoreCase("media:thumbnail")){
                            String url  =current.getAttributes().item(0).getTextContent();
                            item.setThumbnailUrl(url);
                        }
                    }
                    feedItems.add(item);



                }
            }

        }
    }

    public Document getData(){
        try {
            url = new URL(address);
           // url = new URL(address2);
            HttpURLConnection connection =(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDoc = builder.parse(inputStream);
            return xmlDoc;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
