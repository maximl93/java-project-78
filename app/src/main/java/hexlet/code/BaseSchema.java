package hexlet.code;

abstract class BaseSchema<T> {

    public boolean required;

    public BaseSchema() {
        this.required = false;
    }

    public <V extends BaseSchema<T>> V required() { // обобщенный метод возвращает экземпляр дочернего класса
        this.required = true;
        return (V) this; // ???
    }

    public boolean isValid(T checkedValue) {
        if (required) {
            return isRequired(checkedValue);
        } else {
            return isNotRequired(checkedValue);
        }
    }

    /*
    Абстрактные методы, которые будут переопределены так как у StringSchema и NumberSchema разная логика проверки
     */
    abstract boolean isRequired(T checkedValue);

    abstract boolean isNotRequired(T checkedValue);
}
