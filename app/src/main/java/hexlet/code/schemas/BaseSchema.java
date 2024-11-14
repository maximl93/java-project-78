package hexlet.code.schemas;

public abstract class BaseSchema<T> {

    public boolean required;

    public BaseSchema() {
        this.required = false;
    }

    public <V extends BaseSchema<T>> V required() { // обобщенный метод возвращает экземпляр дочернего класса
        this.required = true;
        return (V) this; // ???
    }

    public boolean isValid(T testedValue) {
        if (required) {
            return isRequired(testedValue);
        } else {
            return isNotRequired(testedValue);
        }
    }

    /*
    Абстрактные методы, которые будут переопределены так как у StringSchema и NumberSchema разная логика проверки
     */
    abstract boolean isRequired(T testedValue);

    abstract boolean isNotRequired(T testedValue);
}
