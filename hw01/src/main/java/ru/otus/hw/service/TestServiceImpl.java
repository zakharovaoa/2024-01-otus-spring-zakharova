package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.exceptions.QuestionReadException;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public void executeTest() {
        try {
            ioService.printLine("");
            ioService.printFormattedLine("Please answer the questions below%n");
            var listQuestion = questionDao.findAll();
            if (listQuestion == null) {
                ioService.printLine("Ошибка считывания вопросов");
                return;
            }
            for (var question : listQuestion) {
                ioService.printLine(question.text());
                for (var answer : question.answers()) {
                    ioService.printFormattedLine(answer.text());
                }
                ioService.printLine("");
            }
        } catch (QuestionReadException questionReadException) {
            ioService.printLine(questionReadException.getMessage());
        }
    }
}
