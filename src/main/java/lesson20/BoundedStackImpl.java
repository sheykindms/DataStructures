package lesson20;

import java.util.*;

/***
 * АТД BoundedStack
 * @param <T>
 */
interface BoundedStack<T> {

    int STATUS_POP_NIL = 0; //pop() ещё не вызывалась
    int STATUS_POP_OK = 1;
    int STATUS_POP_ERR = 2;

    int STATUS_PUSH_NIL = 0;
    int STATUS_PUSH_OK = 1;
    int STATUS_PUSH_ERR = 2;

    int STATUS_PEEK_NIL = 0;
    int STATUS_PEEK_OK = 1;
    int STATUS_PEEK_ERR = 2;

    //Предусловие: стек не пустой
    //Постусловие: элемент удалён из стека
    void pop();
    //Предусловие: кол-во элементов стека не выше максимального значения
    //Постусловие: элемент добавлен на верх стека
    void push(T val);
    void clear();
    T peek();
    int size();
    int getPopStatus();
    int getPeekStatus();
    int getPushStatus();
}


/**
 * Реализация АТД BoundedStack
 * @param <T>
 */
public class BoundedStackImpl<T> implements BoundedStack<T> {

    private int peekStatus;
    private int popStatus;
    private int pushStatus;

    private final List<T> stack;
    private final int maximumCapacity;

    //Проставляем инит статусы по командам и запросам в NIL
    public BoundedStackImpl(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
        stack = new ArrayList<>(maximumCapacity);
        pushStatus = STATUS_PUSH_NIL;
        popStatus = STATUS_POP_NIL;
        peekStatus = STATUS_PEEK_NIL;
    }

    //Если pop невозможен - статус ERR, иначе OK
    @Override
    public void pop() {
        if (stack.isEmpty()) {
            popStatus = STATUS_POP_ERR;
        } else {
            stack.remove(0);
            popStatus = STATUS_POP_OK;
        }
    }

    //Если push невозможен - статус ERR, иначе OK
    @Override
    public void push(T val) {
        if (size() < maximumCapacity) {
            stack.add(0, val);
            pushStatus = STATUS_PUSH_OK;
        } else {
            pushStatus = STATUS_PUSH_ERR;
        }
    }

    //Возвращаем все статусы в инит состояние
    @Override
    public void clear() {
        stack.clear();
        pushStatus = STATUS_PUSH_NIL;
        popStatus = STATUS_POP_NIL;
        peekStatus = STATUS_PEEK_NIL;
    }

    //Если peek невозможен - статус ERR, иначе OK
    @Override
    public T peek() {
        if (stack.isEmpty()) {
            peekStatus = STATUS_PEEK_ERR;
            return null;
        }
        peekStatus = STATUS_PEEK_OK;
        return stack.get(0);
    }

    @Override
    public int size() {
        return stack.size();
    }

    public int getPopStatus() {
        return popStatus;
    }

    public int getPeekStatus() {
        return peekStatus;
    }

    public int getPushStatus() {
        return pushStatus;
    }
}
