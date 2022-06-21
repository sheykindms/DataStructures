package ADT;

public abstract class LinkedList {
    private Node head;
    private Node tail;
    private Node cursor;

    //конструктор
    public LinkedList() {
    }


    //ОПЕРАЦИИ:

    //установить курсор на первый узел в списке;
    //предусловие: список не пустой
    //постусловие: курсор установлен на первый узел
    public abstract void head();
    //установить курсор на последний узел в списке;
    //предусловие: список не пустой
    //постусловие: курсор установлен на последний узел
    public abstract void tail();
    //сдвинуть курсор на один узел вправо;
    //предусловие: список не пустой, есть элемент справа
    //постусловие: курсор сдвинут вправо
    public abstract void right();
    //вставить следом за текущим узлом новый узел с заданным значением
    //предусловие: список не пустой
    //постусловие: справа от курсора добавлен новый элемент
    public abstract void put_right(Node item);
    //вставить перед текущим узлом новый узел с заданным значением
    //предусловие: список не пустой
    //постусловие: слева от курсора добавлен новый элемент
    public abstract void put_left(Node item);
    //удалить текущий узел(курсор смещается к правому соседу, если он есть, в противном случае курсор смещается к левому соседу,если он есть)
    //предусловие: список не пустой
    //текущий узел удалён, курсор указывает на правый элемент. Если он отсутствует - на левый
    public abstract void remove();
    //очистить список;
    //постусловие: список очищен
    public abstract void clear();
    //добавить новый узел в хвост списка;
    //постусловие: элемент добавлен в хвост
    public abstract void add_tail(Node item);
    //удалить в списке все узлы с заданным значением;
    //постусловие: удалены всё с заданым значением
    public abstract void remove_all(int value);
    //заменить значение текущего узла на заданное;
    //предусловие: список не пустой
    //постусловие: значение текущего узла заменено на новое
    public abstract void replace(Node item);
    //установить курсор на следующий узел с искомым значением (по отношению к текущему узлу)
    //постусловие: курсор установлен на искомом
    public abstract void find(int value);


    //ЗАПРОСЫ:

    //получить значение текущего узла
    //предусловие: список не пустой
    public abstract void get();
    //Кол-во нод в листе
    public abstract void size();
    //находится ли курсор в начале списка?
    public abstract boolean isHead();
    //находится ли курсор в конце списка?
    public abstract boolean isTail();
    //установлен ли курсор на какой-либо узел в списке (по сути, непустой ли список)
    public abstract boolean isValue();

    //Запросы статусов
    public abstract int getHeadStatus(); // ok; nil
    public abstract int getTailStatus(); // ok; nil
    public abstract int getRightStatus(); // ok; nil
    public abstract int getPutRightStatus(); // ok; err
    public abstract int getPutLeftStatus(); // ok; err
    public abstract int getRemoveStatus(); // ok; err
    public abstract int getReplaceStatus(); // ok; err
    public abstract int getFindStatus(); // ok; nil; err
    public abstract int getGetStatus(); // ok; err

}

class Node {
    private final int value;
    private Node next;

    Node(int value) {
        this.value = value;
        next = null;
    }

    int getValue() {
        return value;
    }

    Node getNext() {
        return next;
    }

    void setNext(Node next) {
        this.next = next;
    }
}

/*
        2.2. Почему операция tail не сводима к другим операциям (если исходить из эффективной реализации)?
            Доступ к последней ноде осуществляется за константное время и операция по сути жёстко привязана к переменной

        2.3. Операция поиска всех узлов с заданным значением, выдающая список таких узлов, уже не нужна. Почему?
            ---
*/