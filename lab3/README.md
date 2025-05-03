**Gỉai thích ý nghĩa đoạn code sau: setContentView(R.layout.activity_main)**
setContentView(...):
Đây là một phương thức của lớp Activity (hoặc các lớp con của nó như AppCompatActivity).
Tên của nó nói lên chức năng: "set content view" - tức là đặt/thiết lập "view nội dung" cho Activity. "View nội dung" chính là toàn bộ giao diện mà người dùng sẽ nhìn thấy và tương tác trên màn hình của Activity đó.
R.layout.activity_main:
R: Đây là một lớp (class) được tạo tự động bởi công cụ xây dựng Android (Android build tools). Nó chứa các ID (định danh) cho tất cả các tài nguyên (resources) trong project của bạn (hình ảnh, chuỗi ký tự, layout, màu sắc, kích thước,...). Nó giống như một "mục lục" để bạn truy cập tài nguyên từ code Java/Kotlin.
.layout: Đây là một lớp con tĩnh (static nested class) bên trong lớp R. Nó chứa các ID cho tất cả các file layout XML nằm trong thư mục res/layout/ của project.
.activity_main: Đây là một hằng số kiểu số nguyên (static final int) bên trong lớp R.layout. Tên activity_main tương ứng trực tiếp với tên của một file layout XML trong thư mục res/layout/, cụ thể là file activity_main.xml. Android Studio tự động tạo ra ID này khi bạn tạo hoặc sửa file activity_main.xml.
Quá trình diễn ra khi gọi setContentView():
Khi dòng code này được thực thi:
Hệ thống Android sử dụng ID R.layout.activity_main để tìm đến file activity_main.xml.
Nó "thổi phồng" (inflate) file XML đó. Quá trình "inflate" có nghĩa là đọc cấu trúc XML và tạo ra các đối tượng View (như LinearLayout, TextView, Button,...) tương ứng trong bộ nhớ, xây dựng nên cây phân cấp View như đã định nghĩa trong XML.
Cây View vừa được tạo ra này sẽ được gắn vào cửa sổ (Window) của Activity, trở thành giao diện chính mà người dùng nhìn thấy.
Tóm lại:
setContentView(R.layout.activity_main) là câu lệnh quan trọng để nói cho Activity biết nó cần hiển thị giao diện nào, bằng cách lấy thiết kế từ file activity_main.xml, biến nó thành các đối tượng View thực tế và đặt chúng lên màn hình. Nếu không có dòng lệnh này (hoặc một cách thiết lập giao diện tương đương), Activity sẽ chỉ hiển thị một màn hình trống.

**edt1 = (EditText) findViewById(R.id.edta)**
- biến edt1 là một đối tượng EditText, trỏ đến đối tượng EditText có id là edta.

**int a = Integer.parseInt(edt1.getText() + "0")**
- Lấy giá trị từ EditText edt1, chuyển đổi nó thành kiểu số nguyên (int) và gán cho biến a.

**if(b==0)..... xử lí logic nếu số chia bằng 0 thì hiển thị thông báo ko hợp lệ, còn khác không thì đơn giản là hiển thị kết qủa ra editText**

