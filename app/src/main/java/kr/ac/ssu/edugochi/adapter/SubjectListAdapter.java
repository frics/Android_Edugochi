package kr.ac.ssu.edugochi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import kr.ac.ssu.edugochi.R;
import kr.ac.ssu.edugochi.activity.MeasureActivity;
import kr.ac.ssu.edugochi.object.SubjectListItem;
import kr.ac.ssu.edugochi.view.ViewHolder;


public class SubjectListAdapter extends BaseAdapter {
    private Context context;
    private ViewHolder viewHolder;
    private ArrayList<SubjectListItem> subjects = new ArrayList<>();
    private MaterialButton measure_subject;
    private TextView subject_title;
    private TextView subject_timeout;

    // 생성자에 Context만 넘겨주는 경우
    public SubjectListAdapter (Context context) {
        this.context = context;
    }

    // 생성자에 Context와 리스트를 넘겨주는 경우
    public SubjectListAdapter (Context context, ArrayList<SubjectListItem> subjects) {
        this.context = context;
        this.subjects = subjects;
    }

    @Override
    public int getCount() {
        return subjects.size();
    }

    @Override
    public SubjectListItem getItem(int position) {
        return subjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        // 해당 과목의 측정된 시간 총 합
        long timeout = subjects.get(position).getTimeout();
        // position : 리스트의 인덱스
        // view     : 항목 레이아웃

        // 만약 이전에 적용된 레이아웃 사례가 없으면 새로운 뷰를 생성
        // 아닐 경우 뷰 재사용
        if (view == null) {
            // 레이아웃 생성
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_subject, viewGroup, false);

            // 뷰 홀더 생성
            viewHolder = new ViewHolder();

            viewHolder.measure_subject = view.findViewById(R.id.measure_subject);
            viewHolder.subject_title = view.findViewById(R.id.subject_string);
            viewHolder.subject_timeout = view.findViewById(R.id.subject_timeout);

            /* MyItem에 아이템을 setting한다. */
            viewHolder.subject_title.setText(subjects.get(position).getSubject());
            viewHolder.subject_timeout.setText(String.format("%02d : %02d : %02d", timeout/1000/60/60, timeout/1000/60%60, timeout/1000%60%60));
            viewHolder.measure_subject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MeasureActivity.class);
                    intent.putExtra("subject", subjects.get(position).getSubject());
                    context.startActivity(intent);
                }
            });
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        return view;
    }

    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void addItem(String subject, long timeout) {

        SubjectListItem item = new SubjectListItem(subject, timeout);
        subjects.add(item);

    }
}
