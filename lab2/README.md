Trả lời các câu hỏi lab2:
-	Khi khởi động chương trình, những sự kiện được gọi là onCreate, onStart, onResume. Và trạng thái hiện tại của MainActivity là onResume.
-	Khi bấm vào nút Call Dialog, những sự kiện được gọi đối với MainActivity là onPause, trạng thái của subactivity là onCreate, onStart, onResume.
-	Khi bấm vào nút Ok thì MainActivity (đang ở trạng thái Stopped hoặc Paused) sẽ được gọi các phương thức như onRestart() (nếu nó đã bị stop), onStart(), và onResume() để trở lại trạng thái hiển thị và tương tác được., Khi bạn nhấn nút "OK" trên Subactivity (giả sử nút này được lập trình để đóng Subactivity, thường bằng cách gọi phương thức finish()) và quay trở lại MainActivity, thì các phương thức vòng đời sau của Subactivity sẽ được gọi theo thứ tự này để "dọn dẹp" và đóng nó lại:
onPause():
Được gọi đầu tiên khi Subactivity bắt đầu mất focus (không còn là Activity tương tác chính trên màn hình nữa, vì MainActivity sắp xuất hiện).
Mục đích: Tạm dừng các hoạt động không nên chạy khi Activity không ở trạng thái foreground (ví dụ: dừng animation, camera, sensor listeners). Đây là nơi rất quan trọng để lưu trữ nhanh chóng bất kỳ dữ liệu nào cần được giữ lại (như nội dung đang soạn thảo) vì Activity có thể bị hệ thống hủy bỏ sau bước này nếu thiếu bộ nhớ.
Trạng thái Activity: Activity đang bị tạm dừng (Paused), vẫn còn tồn tại nhưng không có focus.
onStop():
Được gọi khi Subactivity không còn hiển thị cho người dùng nữa (vì MainActivity đã che phủ hoàn toàn nó).
Mục đích: Thực hiện các công việc "dọn dẹp" nặng hơn, tốn tài nguyên hơn mà không cần thiết phải làm ngay lập tức trong onPause. Ví dụ: Hủy đăng ký các Broadcast Receiver không cần thiết, giải phóng các tài nguyên chỉ cần khi Activity hiển thị.
Trạng thái Activity: Activity đã bị dừng (Stopped), không hiển thị.
onDestroy():
Được gọi cuối cùng, ngay trước khi Activity bị hủy hoàn toàn khỏi bộ nhớ.
Mục đích: Thực hiện công việc "dọn dẹp" cuối cùng. Giải phóng tất cả các tài nguyên mà Activity đang nắm giữ (ví dụ: đóng kết nối mạng, dừng các thread chạy ngầm được tạo bởi Activity này). Sau khi phương thức này kết thúc, đối tượng Activity sẽ bị thu gom rác (garbage collected).
Lưu ý: Phương thức này không phải lúc nào cũng được gọi. Ví dụ, nếu hệ thống cần bộ nhớ gấp, nó có thể kết thúc tiến trình chứa Activity mà không gọi onDestroy(). Đó là lý do việc lưu dữ liệu quan trọng nên thực hiện trong onPause(). Tuy nhiên, khi bạn chủ động gọi finish(), onDestroy() thường sẽ được gọi.
Trạng thái Activity: Activity đang bị hủy (Destroyed).
-	Khi bấm vào nút back trên thiết bị thì đối với MainActivity các sự kiện onPause, onStop được gọi. Trạng thái hiện tại của MainActivity là onStop
Tiếp tục khởi động lại chương trình, sau đó bấm nút Home, những sự kiện được gọi là onPause, onStop, trạng thái hiện tại của MainActivity là onStop.
-	Khi bật lại chương trình thì các sự kiện onRestart, onStart, onResume lần lượt được gọi. Trạng thái hiện tại của MainActivity là onResume
-	Intent class?: Intent là một đối tượng thông điệp (messaging object). Nó giống như một "lá thư" hoặc một "yêu cầu" mà bạn có thể dùng để yêu cầu một hành động từ một thành phần khác của ứng dụng (như một Activity, Service, hoặc Broadcast Receiver). Nó mô tả một ý định thực hiện một hành động nào đó.
Intent dùng cho các mục đích sau:
•	Khởi chạy một Activity: Đây là cách phổ biến nhất để di chuyển giữa các màn hình (Activities) trong ứng dụng của bạn. Đây chính là trường hợp trong ví dụ của bạn.
•	Khởi chạy một Service: Bắt đầu một tác vụ chạy ngầm dưới nền.
•	Gửi một Broadcast: Gửi một thông điệp hệ thống hoặc ứng dụng mà các thành phần khác có thể "lắng nghe" và phản hồi.
•	Truyền dữ liệu: Đóng gói và gửi dữ liệu giữa các thành phần (ví dụ: gửi thông tin người dùng từ màn hình đăng nhập sang màn hình chính).
•	(MainActivity.this, Subactivity.class): Đây là hai tham số được truyền vào hàm khởi tạo của Intent:
MainActivity.this: Tham số đầu tiên là Context. Context cung cấp thông tin về môi trường ứng dụng. MainActivity.this chỉ định rằng Intent này được tạo ra từ bên trong Activity có tên là MainActivity. this là một từ khóa tham chiếu đến đối tượng hiện tại của lớp MainActivity.
Subactivity.class: Tham số thứ hai là lớp (Class) của thành phần mà bạn muốn khởi chạy. Ở đây, bạn đang chỉ định rõ ràng rằng bạn muốn khởi chạy Activity có tên là Subactivity. Việc chỉ định rõ ràng lớp đích như thế này tạo ra một Intent tường minh (Explicit Intent).
Tóm lại dòng lệnh này: Bạn đang tạo ra một đối tượng Intent mới, đặt tên là intent1. Intent này mang "ý định" là khởi chạy Subactivity từ MainActivity.

