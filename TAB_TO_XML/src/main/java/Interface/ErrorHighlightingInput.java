package Interface;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import org.reactfx.Subscription;

import javafx.concurrent.Task;
import javafx.scene.control.TextArea;

public class ErrorHighlightingInput {

	protected static ExecutorService executor = Executors.newSingleThreadExecutor();
	private CodeArea codeArea;
	private TextArea textArea;

	public ErrorHighlightingInput(CodeArea textArea){
		this.codeArea = textArea;
	}

	public Task<StyleSpans<Collection<String>>> computeHighlightingAsync() {
		String text = codeArea.getText();

		Task<StyleSpans<Collection<String>>> task = new Task<>() {
			@Override
			protected StyleSpans<Collection<String>> call() {
				return computeHighlighting(text);
			}
		};
		executor.execute(task);
		task.isDone();
		return task;
	}

	private static StyleSpans<Collection<String>> computeHighlighting(String text) {

		StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
		spansBuilder.add(Collections.emptyList(), 5);
		spansBuilder.add(Collections.singleton("Error"), 5);
		spansBuilder.add(Collections.emptyList(), text.length() - 10);
		return spansBuilder.create();
	}


	private void applyHighlighting(StyleSpans<Collection<String>> highlighting) {
		codeArea.setStyleSpans(0, highlighting);
	}

	public void enableHighlighting() {
		Subscription cleanupWhenDone = this.codeArea.multiPlainChanges()
				.successionEnds(Duration.ofMillis(350))
				.supplyTask(this::computeHighlightingAsync)
				.awaitLatest(this.codeArea.multiPlainChanges())
				.filterMap(t -> {
					if(t.isSuccess()) {
						return Optional.of(t.get());
					} else {
						t.getFailure().printStackTrace();
						return Optional.empty();
					}
				})
				.subscribe(this::applyHighlighting);
	}



}
