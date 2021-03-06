package cn.bluemobi.dylan.adaptertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bluemobi.dylan.commonadapter.ablistview.CommonAdapter;
import cn.bluemobi.dylan.commonadapter.ablistview.CommonViewHolder;


public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> datas;
    private CommonAdapter commonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commonAdapterTest();
    }

    /**
     * 普通适配器的方法
     */
    private void myAdapterTest() {
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listview);
        List<String> datas = new ArrayList<>();
        datas.add("普通适配器测试1");
        datas.add("普通适配器测试2");
        datas.add("普通适配器测试3");
        datas.add("普通适配器测试4");
        listView.setAdapter(new MyAdapter(this, datas));
    }

    /**
     * 万能适配器的方法
     */
    private void commonAdapterTest() {
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listview);
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            datas.add("万能适配器测试" + i);
        }
        listView.setAdapter(new CommonAdapter<String>(this, datas, R.layout.item) {

            @Override
            protected void convertView(CommonViewHolder commonViewHolder, String s) {
               TextView textView= commonViewHolder.get(R.id.textView);
                textView.setText(s);
            }


        });
    }



    /**
     * 第一种方法 更新对应view的内容
     *
     * @param position 要更新的位置
     */
    private void updateSingle(int position) {
        /**第一个可见的位置**/
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        /**最后一个可见的位置**/
        int lastVisiblePosition = listView.getLastVisiblePosition();

        /**在看见范围内才更新，不可见的滑动后自动会调用getView方法更新**/
        if (position >= firstVisiblePosition && position <= lastVisiblePosition) {
            /**获取指定位置view对象**/
            View view = listView.getChildAt(position - firstVisiblePosition);
            TextView textView = (TextView) view.findViewById(R.id.textView);
            textView.setText(datas.get(position));
        }
    }

    /**
     * 第二种方法 找出对应的ViewHolder，通过ViewHolder去设置值
     *
     * @param position 要更新的位置
     */
    private void updateOne(int position) {
        /**第一个可见的位置**/
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        /**最后一个可见的位置**/
        int lastVisiblePosition = listView.getLastVisiblePosition();

        /**在看见范围内才更新，不可见的滑动后自动会调用getView方法更新**/
        if (position >= firstVisiblePosition && position <= lastVisiblePosition) {
            /**获取指定位置view对象**/
            View view = listView.getChildAt(position - firstVisiblePosition);
            /**通过ViewHolder找出缓存的对应控件**/
            TextView textView = CommonViewHolder.get(view, R.id.textView);
            textView.setText(datas.get(position));

        }
    }

    /**
     * 第三种方法 调用一次getView()方法,比较省事，谷歌推荐
     *
     * @param position 要更新的位置
     */
    private void updateItem(int position) {
        /**第一个可见的位置**/
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        /**最后一个可见的位置**/
        int lastVisiblePosition = listView.getLastVisiblePosition();

        /**在看见范围内才更新，不可见的滑动后自动会调用getView方法更新**/
        if (position >= firstVisiblePosition && position <= lastVisiblePosition) {
            /**获取指定位置view对象**/
            View view = listView.getChildAt(position - firstVisiblePosition);
            commonAdapter.getView(position, view, listView);
        }

    }
}
