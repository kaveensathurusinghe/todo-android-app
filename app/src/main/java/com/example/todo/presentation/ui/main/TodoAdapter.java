package com.example.todo.presentation.ui.main;

import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.domain.model.Todo;
import com.example.todo.utils.DateUtils;

public class TodoAdapter extends ListAdapter<Todo, TodoAdapter.TodoViewHolder> {

	public interface TodoInteractionListener {
		void onTodoClicked(Todo todo);

		void onTodoCheckedChanged(Todo todo, boolean isChecked);

		void onTodoDeleteClicked(Todo todo);
	}

	private final TodoInteractionListener listener;

	public TodoAdapter(TodoInteractionListener listener) {
		super(DIFF_CALLBACK);
		this.listener = listener;
	}

	private static final DiffUtil.ItemCallback<Todo> DIFF_CALLBACK = new DiffUtil.ItemCallback<Todo>() {
		@Override
		public boolean areItemsTheSame(@NonNull Todo oldItem, @NonNull Todo newItem) {
			return oldItem.getId() == newItem.getId();
		}

		@Override
		public boolean areContentsTheSame(@NonNull Todo oldItem, @NonNull Todo newItem) {
		    boolean sameTitle = (oldItem.getTitle() == null && newItem.getTitle() == null)
			    || (oldItem.getTitle() != null && oldItem.getTitle().equals(newItem.getTitle()));
		    boolean sameDescription = (oldItem.getDescription() == null && newItem.getDescription() == null)
			    || (oldItem.getDescription() != null && oldItem.getDescription().equals(newItem.getDescription()));
		    boolean sameTags = (oldItem.getTags() == null && newItem.getTags() == null)
			    || (oldItem.getTags() != null && oldItem.getTags().equals(newItem.getTags()));

		    return oldItem.isCompleted() == newItem.isCompleted()
			    && sameTitle
			    && sameDescription
			    && oldItem.getDueDateMillis() == newItem.getDueDateMillis()
			    && sameTags;
		}
	};

	@NonNull
	@Override
	public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
		return new TodoViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
		holder.bind(getItem(position));
	}

	class TodoViewHolder extends RecyclerView.ViewHolder {

		private final CheckBox cbCompleted;
		private final TextView tvTitle;
		private final TextView tvDueDate;
		private final TextView tvTags;
		private final ImageButton btnDelete;

		TodoViewHolder(@NonNull View itemView) {
			super(itemView);
			cbCompleted = itemView.findViewById(R.id.cbCompleted);
			tvTitle = itemView.findViewById(R.id.tvTitle);
			tvDueDate = itemView.findViewById(R.id.tvDueDate);
			tvTags = itemView.findViewById(R.id.tvTags);
			btnDelete = itemView.findViewById(R.id.btnDelete);
		}

		void bind(Todo todo) {
		    // Subtle entry animation for better perceived smoothness
		    android.view.animation.Animation fadeIn = android.view.animation.AnimationUtils.loadAnimation(
			    itemView.getContext(),
			    R.anim.item_fade_in
		    );
		    itemView.startAnimation(fadeIn);
			tvTitle.setText(todo.getTitle());
			tvDueDate.setText(DateUtils.formatDate(todo.getDueDateMillis()));
			tvTags.setText(todo.getTags());
			cbCompleted.setChecked(todo.isCompleted());

			itemView.setOnClickListener(v -> {
				v.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP);
				listener.onTodoClicked(todo);
			});

			cbCompleted.setOnCheckedChangeListener((buttonView, isChecked) -> {
				buttonView.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP);
				listener.onTodoCheckedChanged(todo, isChecked);
			});

			btnDelete.setOnClickListener(v -> {
				v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
				listener.onTodoDeleteClicked(todo);
			});
		}
	}
}
