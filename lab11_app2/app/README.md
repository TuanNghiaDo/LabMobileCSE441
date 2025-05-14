# Đối tượng Intent
## Tác dụng:
- Khởi động Activity: Bắt đầu một màn hình/giao diện mới.
- Khởi động Service: Bắt đầu một tác vụ chạy nền.
- Gửi Broadcast: Phát đi một thông báo mà các Broadcast Receiver có thể lắng nghe.
- Truyền dữ liệu: Đính kèm dữ liệu (gọi là "extras") để gửi cùng với Intent.
## Constructors (Hàm tạo): Có nhiều hàm tạo để tạo đối tượng Intent ban đầu:
- Intent(): Tạo một Intent rỗng.
- Intent(Intent o): Tạo một bản sao của Intent khác.
- Intent(String action): Tạo Intent với một hành động cụ thể.
- Intent(String action, Uri uri): Tạo Intent với hành động và dữ liệu (URI).
- Intent(Context packageContext, Class<?> cls): Tạo một Explicit Intent để khởi động một Activity hoặc Service trong package của bạn.
## Setters (Thiết lập): Dùng để cấu hình Intent sau khi tạo. Các phương thức này thường trả về chính đối tượng Intent (this) để cho phép gọi chuỗi (chaining).
- Intent setAction(String action): Thiết lập hành động cho Intent.
- Intent setData(Uri data): Thiết lập dữ liệu (URI) cho Intent.
- Intent setType(String type): Thiết lập kiểu MIME của dữ liệu cho Intent (ví dụ: "image/jpeg", "text/plain").
- Intent setDataAndType(Uri data, String type): Thiết lập cả dữ liệu và kiểu MIME. 
Lưu ý: nếu bạn gọi setData và setType riêng biệt, lời gọi thứ hai sẽ hủy bỏ thiết lập của lời gọi đầu tiên. Luôn dùng setDataAndType nếu bạn cần cả hai.
- Intent addCategory(String category): Thêm một category vào Intent.
- Intent setClass(Context packageContext, Class<?> cls) / Intent setClassName(Context packageContext, String className) / Intent setComponent(ComponentName component): 
Thiết lập component đích cho Explicit Intent.
- Intent putExtra(String name, Xxx value): Thêm dữ liệu phụ (extras) vào Intent, 
nơi Xxx là các kiểu dữ liệu nguyên thủy, String, Parcelable, Serializable, hoặc mảng của chúng. 
Có nhiều phiên bản quá tải (overloaded) cho các kiểu dữ liệu khác nhau (ví dụ: putExtra(String name, String value), putExtra(String name, int value), putExtra(String name, Bundle value)).
- Intent putExtras(Bundle extras): Thêm toàn bộ một Bundle làm extras vào Intent.
- Intent addFlags(int flags): Thêm các cờ (flags) vào Intent để kiểm soát cách hệ thống xử lý Intent 
(ví dụ: cách stack Activity được quản lý). Có rất nhiều cờ (FLAG_ACTIVITY_NEW_TASK, FLAG_ACTIVITY_CLEAR_TOP, v.v.), mỗi cờ có tác dụng riêng.
- Intent setFlags(int flags): Thiết lập các cờ cho Intent, ghi đè lên các cờ hiện có.
## Getters (Truy xuất): Dùng để lấy thông tin từ Intent.
String getAction(): Lấy hành động của Intent.
Uri getData(): Lấy dữ liệu (URI) của Intent.
String getType(): Lấy kiểu MIME của dữ liệu.
Set<String> getCategories(): Lấy tập hợp các category của Intent.
Bundle getExtras(): Lấy tất cả extras dưới dạng một Bundle. Trả về null nếu không có extras nào.
Xxx getXxxExtra(String name, Xxx defaultValue): Lấy một giá trị extras theo tên và kiểu dữ liệu. Có các phiên bản cho các kiểu dữ liệu khác nhau (ví dụ: getStringExtra, getIntExtra, getParcelableExtra). Luôn cung cấp giá trị mặc định (defaultValue) để tránh NullPointerException.
int getFlags(): Lấy các cờ của Intent.
ComponentName getComponent(): Lấy component đích cho Explicit Intent.

# Intent Filter (Bộ lọc Intent) là một cơ chế mạnh mẽ được khai báo trong tệp AndroidManifest.xml.
## Tác dụng chi tiết của Intent Filter
- Mục đích chính: Cho phép các thành phần (Activity, Service, Broadcast Receiver) của ứng dụng công khai "khả năng" của mình cho hệ thống Android và các ứng dụng khác. 
Khi một _Implicit Intent_ được phát ra, hệ thống sử dụng _Intent Filter_ để tìm kiếm một hoặc nhiều thành phần có khả năng xử lý Intent đó.
- Cơ chế khám phá (Discovery): Intent Filter cho phép các ứng dụng khám phá các chức năng được cung cấp bởi các ứng dụng khác mà không cần biết tên lớp cụ thể của chúng. 
Ví dụ, khi bạn muốn mở một file PDF, hệ thống sẽ tìm tất cả các Activity đã khai báo Intent Filter có thể VIEW dữ liệu có MIME type là application/pdf và hiển thị cho người dùng lựa chọn.
- Liên kết lỏng lẻo (Loose Coupling): Intent Filter tạo ra sự liên kết lỏng lẻo giữa các thành phần và ứng dụng. 
- Thành phần gửi Intent không cần biết ai sẽ nhận nó, chỉ cần biết hành động cần thực hiện và dữ liệu liên quan.
- Matching (Khớp): Một Intent ngầm định sẽ được phân phối đến một thành phần nếu nó khớp với ít nhất một trong các _<intent-filter>_ được khai báo cho thành phần đó. 
Để một Intent khớp với một <intent-filter>, nó phải vượt qua cả ba bài kiểm tra:
- Action Test: Action trong Intent phải khớp với một trong những <action> được liệt kê trong filter. Nếu Intent không có action, nó sẽ chỉ khớp với filter không có action nào được liệt kê.
- Category Test: Mỗi category trong Intent phải khớp với một trong những <category> được liệt kê trong filter. 
Hầu hết các Intent ngầm định được gửi bởi _startActivity()_ và _sendBroadcast()_ đều có _category android.intent.category.DEFAULT_ được tự động thêm vào. 
Do đó, để Activity của bạn có thể nhận các Intent ngầm định chung, Intent Filter của nó phải bao gồm _<category android:name="android.intent.category.DEFAULT"/>_.
- Data Test: Dữ liệu (URI và kiểu MIME) trong Intent phải khớp với một trong những <data> được liệt kê trong filter. Kiểm tra này phức tạp hơn, bao gồm so khớp scheme, authority, path và MIME type.
- Khai báo: Intent Filter được khai báo dưới dạng thẻ _<intent-filter>_ là con của thẻ <activity>, <service>, hoặc <receiver> trong _AndroidManifest.xml_.

# Đối tượng Uri (Uniform Resource Identifier)
- Tác dụng: Uri được sử dụng rộng rãi trong Android để:
- Xác định dữ liệu cho các Intent (ví dụ: hiển thị một trang web, mở một file).
- Truy cập dữ liệu thông qua Content Provider.
- Làm việc với hệ thống file.
# Các phương thức Uri thường dùng:
## static Uri parse(String uriString):
- Ý nghĩa: Phương thức tĩnh này dùng để phân tích cú pháp một chuỗi String thành một đối tượng Uri. Đây là cách phổ biến nhất để tạo một đối tượng Uri từ một chuỗi URL hoặc chuỗi định danh tài nguyên khác.
-  Ví dụ: Uri webpage = Uri.parse("http://www.example.com");
-  String toString():
-  Ý nghĩa: Chuyển đổi đối tượng Uri trở lại dạng chuỗi String gốc của nó.
-  Ví dụ: String uriString = data.toString(); (như trong đoạn code trước của bạn)
-  String getScheme():
-  Ý nghĩa: Trả về scheme của URI (phần đứng trước dấu : đầu tiên). Ví dụ: "http", "https", "file", "content", "tel", "geo". Trả về null nếu không có scheme.
-  Ví dụ: String scheme = uri.getScheme(); // Có thể trả về "http"
-  String getAuthority():
-  Ý nghĩa: Trả về authority của URI (phần nằm giữa scheme và path, sau dấu //). Đối với URL, authority thường là tên host và tùy chọn cổng (host:port). Đối với content URI, đó là tên authority của Content Provider.
-  Ví dụ: String authority = uri.getAuthority(); // Đối với http://www.example.com/path -> "www.example.com"
-  String getPath():
 - Ý nghĩa: Trả về đường dẫn (path) của URI (phần nằm sau authority và trước query/fragment).
-  Ví dụ: String path = uri.getPath(); // Đối với http://www.example.com/path?query -> "/path"
-  String getQuery():
-  Ý nghĩa: Trả về chuỗi query của URI (phần nằm sau dấu ?).
-  Ví dụ: String query = uri.getQuery(); // Đối với http://www.example.com/path?query=value -> "query=value"
-  String getFragment():
-  Ý nghĩa: Trả về fragment của URI (phần nằm sau dấu #).
-  Ví dụ: String fragment = uri.getFragment(); // Đối với http://www.example.com/path#section -> "section"
-  Uri.Builder buildUpon():
-  Ý nghĩa: Trả về một đối tượng Uri.Builder được khởi tạo với các thành phần của URI hiện tại. Builder này cho phép bạn dễ dàng thêm, sửa đổi các phần của URI (scheme, authority, path, query parameter, fragment) một cách an toàn.