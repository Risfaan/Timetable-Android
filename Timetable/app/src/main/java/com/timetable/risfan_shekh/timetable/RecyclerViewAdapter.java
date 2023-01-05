package com.timetable.risfan_shekh.timetable;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ClassViewHolder> {

    List<Classes_mon> class_mon;
    int pos;
    List<assignments_mon> ass_mon;
    List<teacher_mon> t_mon;
    List<exam_mon> e_mon;
    public ImageView mDeleteImage;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void  onItemClick(int position);
        void  onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){mListener = listener;}


    public RecyclerViewAdapter(List<Classes_mon> class_mon) {
        this.class_mon = class_mon;
        pos=0;


    }
    public RecyclerViewAdapter(String s,List<assignments_mon> ass_mon) {
        this.ass_mon = ass_mon;
        pos=1;

    }

    public RecyclerViewAdapter(String abc, String abc1, List<teacher_mon> t_mon) {
        this.t_mon = t_mon;
        pos=2;
    }

    public RecyclerViewAdapter(String abc, String abc1, String abc2, List<exam_mon> e_mon) {
        this.e_mon = e_mon;
        pos=3;
    }


    @Override
    public ClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ClassViewHolder cvh = null;
        if(pos==0) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            View view = inflater.inflate(R.layout.class_cardview, parent, false);
            cvh = new ClassViewHolder(view,mListener);
        }
        else if(pos==1)
        {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            View view = inflater.inflate(R.layout.ass_cardview, parent, false);
            cvh = new ClassViewHolder(view,mListener);
        }
        else if(pos==2)
        {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            View view = inflater.inflate(R.layout.teacher_cardview, parent, false);
            cvh = new ClassViewHolder(view,mListener);
        }
        else if(pos==3)
        {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            View view = inflater.inflate(R.layout.exam_cardview, parent, false);
            cvh = new ClassViewHolder(view,mListener);
        }

        return cvh;
    }

    @Override
    public void onBindViewHolder(ClassViewHolder holder, int position) {

        if(pos==0) {
            holder.start_end.setText(class_mon.get(position).from_to_mon);
            holder.class_no.setText(class_mon.get(position).room_mon);
            holder.sub.setText(class_mon.get(position).sub_mon);
            holder.teacher.setText(class_mon.get(position).tech_mon);
            holder.id_c.setText(class_mon.get(position).c_id);
            holder.day_cv.setText(class_mon.get(position).day_mon);
        }
        else if(pos==1)
        {
            holder.subject_ass.setText(ass_mon.get(position).sub_mon);
            holder.topic_ass.setText(ass_mon.get(position).title_mon);
            holder.weightage_ass.setText(ass_mon.get(position).weightage_mon);
            holder.desc_ass.setText(ass_mon.get(position).desc_mon);
            holder.date_ass.setText(ass_mon.get(position).ass_date);
            holder.id_a.setText(ass_mon.get(position).a_id);
            //holder..setText(ass_mon.get(position).status_done);
        }
        else if(pos==2)
        {
            holder.t_post.setText(t_mon.get(position).t_post);
            holder.t_phone.setText(t_mon.get(position).t_phone);
            holder.t_office.setText(t_mon.get(position).t_office);
            holder.t_email.setText(t_mon.get(position).t_email);
            holder.t_name.setText(t_mon.get(position).t_name);
            holder.id_t.setText(t_mon.get(position).t_id);

        }
        else if(pos==3)
        {

            holder.e_date.setText(e_mon.get(position).e_date);
            holder.e_fromto.setText(e_mon.get(position).e_from_to);
            holder.e_room.setText(e_mon.get(position).e_room);
            holder.e_sub.setText(e_mon.get(position).e_sub);
            holder.id_e.setText(e_mon.get(position).e_id);

        }

        /*holder.start_end.setText("staart-end");
        holder.class_no.setText("123");
        holder.sub.setText("subject");
        holder.teacher.setText("Teacher");*/
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        int size_arr=0;
        if(pos == 0)
        {
            size_arr = class_mon.size();
        }
        else if(pos == 1)
        {
            size_arr = ass_mon.size();
        }
        else if(pos == 2)
        {
            size_arr = t_mon.size();
        }
        else if(pos == 3)
        {
            size_arr = e_mon.size();
        }
        return size_arr;
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView start_end;
        TextView sub;
        TextView teacher;
        TextView class_no;
        TextView date_ass,topic_ass,weightage_ass,subject_ass,desc_ass,status_done,day_cv;
        TextView t_name,t_post,t_phone,t_email,t_office;
        TextView e_sub,e_room,e_fromto,e_date,id_t,id_c,id_e,id_a;


        public ClassViewHolder(View itemView,final OnItemClickListener listener) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.card_view);
            start_end = (TextView)itemView.findViewById(R.id.start_end);
            sub = (TextView)itemView.findViewById(R.id.sub_card);
            teacher = (TextView)itemView.findViewById(R.id.teacher_card);
            id_t = (TextView)itemView.findViewById(R.id.id_t);
            id_a = (TextView)itemView.findViewById(R.id.id_a);
            id_e = (TextView)itemView.findViewById(R.id.id_e);
            id_c = (TextView)itemView.findViewById(R.id.id_c);
            day_cv=(TextView)itemView.findViewById(R.id.day_cv);
            class_no = (TextView)itemView.findViewById(R.id.class_num_card);
            date_ass = (TextView)itemView.findViewById(R.id.date_ass);
            topic_ass = (TextView)itemView.findViewById(R.id.topic_card_ass);
            weightage_ass =(TextView)itemView.findViewById(R.id.weightage_card);
            subject_ass = (TextView)itemView.findViewById(R.id.subject_card_ass);
            desc_ass = (TextView)itemView.findViewById(R.id.desc_card_ass);
            t_name=(TextView)itemView.findViewById(R.id.name_tcard);
            t_email=(TextView)itemView.findViewById(R.id.email_tcard);
            t_office=(TextView)itemView.findViewById(R.id.office_tcard);
            t_phone=(TextView)itemView.findViewById(R.id.phone_tcard);
            t_post=(TextView)itemView.findViewById(R.id.post_tcard);
            e_sub=(TextView)itemView.findViewById(R.id.sub_ecard);
            e_room=(TextView)itemView.findViewById(R.id.room_ecard);
            e_fromto=(TextView)itemView.findViewById(R.id.fromto_ecard);
            e_date=(TextView)itemView.findViewById(R.id.date_ecard);

            if(pos==0)
            {
                mDeleteImage=itemView.findViewById(R.id.delete_class);
                mDeleteImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(listener!=null)
                        {
                            int position = getAdapterPosition();
                            if(position!=RecyclerView.NO_POSITION){
                                listener.onDeleteClick(position);
                            }
                        }
                    }
                });
            }
            else if(pos==1)
            {
                mDeleteImage=itemView.findViewById(R.id.delete_ass);
                mDeleteImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(listener!=null)
                        {
                            int position = getAdapterPosition();
                            if(position!=RecyclerView.NO_POSITION){
                                listener.onDeleteClick(position);
                            }
                        }
                    }
                });
            }
            else if(pos==2)
            {
                mDeleteImage=itemView.findViewById(R.id.delete_teacher);
                mDeleteImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(listener!=null)
                        {
                            int position = getAdapterPosition();
                            if(position!=RecyclerView.NO_POSITION){
                                listener.onDeleteClick(position);
                            }
                        }
                    }
                });
            }
            else if(pos==3)
            {
                mDeleteImage=itemView.findViewById(R.id.delete_exam);
                mDeleteImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(listener!=null)
                        {
                            int position = getAdapterPosition();
                            if(position!=RecyclerView.NO_POSITION){
                                listener.onDeleteClick(position);
                            }
                        }
                    }
                });
            }
//            mDeleteImage=itemView.findViewById(R.id.delete_teacher);
//
//            mDeleteImage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(listener!=null)
//                    {
//                        int position = getAdapterPosition();
//                        if(position!=RecyclerView.NO_POSITION){
//                            listener.onDeleteClick(position);
//                        }
//                    }
//                }
//            });

        }


    }
}
