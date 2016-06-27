package com.example.wrket.morrismothers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.Iterator;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InputStream inputStream = getResources().openRawResource(R.raw.fietstrommels_maart_2013);
        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> foodList = csvFile.read();
        MyListAdapter adapter=new MyListAdapter(this, R.layout.listrow,R.id.txtid, foodList);
        TextView textView = (TextView) findViewById(R.id.textView);
        int Centrum = new ListIterator().iterate(foodList,"Centrum");
        int Noord = new ListIterator().iterate(foodList,"Noord");
        int Delfshaven = new ListIterator().iterate(foodList,"Delfshaven");
        int Feijenoord = new ListIterator().iterate(foodList,"Feijenoord");
        int Kralingen = new ListIterator().iterate(foodList,"Kralingen/Crooswijk");
        int Charlois = new ListIterator().iterate(foodList,"Charlois");
        int Overschie = new ListIterator().iterate(foodList,"Overschie");
        int Hillegersberg = new ListIterator().iterate(foodList,"Hillegersberg");
        int Ijsselmonde = new ListIterator().iterate(foodList,"Ijsselmonde");
        int Hoogvliet = new ListIterator().iterate(foodList,"Hoogvliet");
        int Omoord = new ListIterator().iterate(foodList,"Omoord");
        int Pernis = new ListIterator().iterate(foodList,"Pernis");
        int West = new ListIterator().iterate(foodList,"West");
        textView.setText("" + Centrum);
        Void chart = new Chart().MakeChart(Centrum,Charlois,Delfshaven,Feijenoord,Hillegersberg,Hoogvliet,Ijsselmonde,Kralingen,Noord,Omoord,Overschie,Pernis,West);
    }

    private class CSVFile {
        InputStream inputStream;

        public CSVFile(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        public List<String[]> read() {
            //
            List<String[]> resultList = new ArrayList<String[]>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] row = line.split(",");
                    resultList.add(row);
                }
            } catch (IOException e) {
                Log.e("Main", e.getMessage());
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e("Main", e.getMessage());
                }
            }

            return resultList;
        }

    }
    public class ListIterator {
        int current = -1;
        int amount = 0;
        String[] yolo;
        ArrayList list2 = new ArrayList();
        public int iterate(List<String[]> foodlist,String plaats){
            Iterator itr = foodlist.iterator() ;
            while (itr.hasNext() ){
                current += 1;
                yolo = foodlist.get(current);
                list2.add(yolo[7]);
                itr.next();

            }
            for (int i=0;i < list2.size();i++)
                if (list2.get(i).equals(plaats)){
                amount += 1;
                }


            return amount ;

        }
    }
    public class Chart{

        public Void MakeChart(int Centrum,int Charlois,int Delfshaven,int Feijenoord,int Hillegersberg,int Hoogvliet,int Ijsselmonde,int Kralingen,int Noord,int Omoord,int Overschie,int Pernis, int West) {
            GraphView graph = (GraphView) findViewById(R.id.graph);
            BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(new DataPoint[]{
                    new DataPoint(0, Centrum),
                    new DataPoint(1, Charlois),
                    new DataPoint(2, Delfshaven),
                    new DataPoint(3, Feijenoord),
                    new DataPoint(4, Hillegersberg),
                    new DataPoint(5, Hoogvliet),
                    new DataPoint(6, Ijsselmonde),
                    new DataPoint(7, Kralingen),
                    new DataPoint(8, Noord),
                    new DataPoint(9, Omoord),
                    new DataPoint(10, Overschie),
                    new DataPoint(11, Pernis),
                    new DataPoint(12, West)
            });
            graph.addSeries(series);
            series.setSpacing(50);
            return null;

        }
    }

}
