package zad2.sk.stuba.fei.oop;

import zad2.sk.stuba.fei.oop.generated.Document;

public abstract class Transformer<T> {

    public abstract T transform(Document document);
}
