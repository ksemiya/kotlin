


* Весело с фреймворками, где инициализация переменных должна происходить не в конструкторе, а в каком-нибудь init-методе: получается куча Type?-фигни и variable!!-фигни

* Метод `readAllBytes`, честно потыренный с StackOverflow, конвертанули в котлин. Результирующий код не компилится, присвоение в котлине не имеет значения:
```java
public class Example1 {
    public static byte[] readAllBytes(InputStream is) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];
        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }
}
```
```kotlin
@Throws(IOException::class)
fun readAllBytes(`is`: InputStream): ByteArray {
    val buffer = ByteArrayOutputStream()
    var nRead: Int
    val data = ByteArray(16384)
    while ((nRead = `is`.read(data, 0, data.size)) != -1) {
        buffer.write(data, 0, nRead)
    }
    buffer.flush()
    return buffer.toByteArray()
}
```
