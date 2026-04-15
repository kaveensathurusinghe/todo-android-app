package com.example.todo.di;

import android.content.Context;

import com.example.todo.data.repository.AuthRepository;
import com.example.todo.data.repository.TodoRepository;
import com.example.todo.domain.usecase.AddTodoUseCase;
import com.example.todo.domain.usecase.DeleteTodoUseCase;
import com.example.todo.domain.usecase.GetTodosUseCase;
import com.example.todo.domain.usecase.LoginUseCase;
import com.example.todo.domain.usecase.RegisterUseCase;
import com.example.todo.domain.usecase.UpdateTodoUseCase;

public final class AppModule {

	private AppModule() {
		// No instances
	}

	public static LoginUseCase provideLoginUseCase(Context context) {
		AuthRepository authRepository = RepositoryModule.provideAuthRepository(context);
		return new LoginUseCase(authRepository);
	}

	public static RegisterUseCase provideRegisterUseCase(Context context) {
		AuthRepository authRepository = RepositoryModule.provideAuthRepository(context);
		return new RegisterUseCase(authRepository);
	}

	public static GetTodosUseCase provideGetTodosUseCase(Context context) {
		TodoRepository todoRepository = RepositoryModule.provideTodoRepository(context);
		return new GetTodosUseCase(todoRepository);
	}

	public static AddTodoUseCase provideAddTodoUseCase(Context context) {
		TodoRepository todoRepository = RepositoryModule.provideTodoRepository(context);
		return new AddTodoUseCase(todoRepository);
	}

	public static UpdateTodoUseCase provideUpdateTodoUseCase(Context context) {
		TodoRepository todoRepository = RepositoryModule.provideTodoRepository(context);
		return new UpdateTodoUseCase(todoRepository);
	}

	public static DeleteTodoUseCase provideDeleteTodoUseCase(Context context) {
		TodoRepository todoRepository = RepositoryModule.provideTodoRepository(context);
		return new DeleteTodoUseCase(todoRepository);
	}
}
