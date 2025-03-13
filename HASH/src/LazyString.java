public class LazyString {
    private String source; // ссылка на исходную строку
    private int start, end; // границы нашей подстроки
    private int hash; // запоминаем хеш чтобы не пересчитывать

    private LazyString() {}

    public LazyString(String source, int start, int end) {
        this.source = source;
        this.start = start;
        this.end = end;
        for (int i = start; i <= end; i++) {
            hash += source.charAt(i);
        }
        System.out.println("Хеш для подстроки'" + source + "' равен " + hash);
    }

    public LazyString shiftRight() {
        LazyString shifted = new LazyString();
        shifted.source = source;
        shifted.start = start + 1;
        shifted.end = end + 1;
        shifted.hash = hash - source.charAt(shifted.start) + source.charAt(shifted.end);
        return shifted;
    }

    public int length() {
        return end - start;
    }

    public boolean equals(LazyString that) {
        // если не равны по длине, то не равны и вовсе
        if (length() != that.length()) {
            return false;
        }

        // перебираем и сравниваем на равенство все символы
        for (int i = 0; i < length(); i++) {
            char myChar = source.charAt(start + i);
            char thatChar = source.charAt(that.start + i);
            if (myChar != thatChar) { // если хотя бы один не совпал, то не равны
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return hash; // хеш у нас всегда предпосчитан для каждого объекта, чтобы не тратить на это время
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LazyString that = (LazyString) o;
        return this.equals(that);
    }

}