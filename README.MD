# LaptopShop – Java Spring Boot MVC

Ứng dụng website bán laptop được xây dựng bằng **Java 17, Spring Boot, Spring MVC, Spring Data JPA, Spring Security, JSP và MySQL**. Dự án có giao diện khách hàng và khu vực quản trị để quản lý sản phẩm, người dùng và đơn hàng.

> Đây là dự án học tập. Cấu hình database trong source hiện đang dùng tài khoản `root` và mật khẩu mẫu `123456`; hãy đổi lại cho phù hợp với máy của bạn, không dùng cấu hình mẫu này khi triển khai công khai.

## Công nghệ sử dụng

- Java 17
- Spring Boot 3.2.2
- Maven (đã kèm Maven Wrapper)
- MySQL
- Spring MVC, Spring Data JPA / Hibernate
- Spring Security
- JSP / JSTL
- HTML, CSS, JavaScript, Bootstrap

## Chức năng chính

- Trang chủ hiển thị danh sách laptop và trang chi tiết sản phẩm.
- Đăng ký và đăng nhập bằng Spring Security.
- Quản trị sản phẩm: xem danh sách, xem chi tiết, thêm, sửa, xóa và tải ảnh lên.
- Khu vực quản trị người dùng, đơn hàng và dashboard.
- Lưu dữ liệu thông qua MySQL và Spring Data JPA.

## Yêu cầu trước khi chạy

1. Cài **JDK 17** và kiểm tra bằng `java -version`.
2. Cài **MySQL Server** (MySQL 8.x được khuyến nghị) và khởi động dịch vụ MySQL.
3. Có thể dùng IntelliJ IDEA hoặc VS Code để mở source.
4. Không bắt buộc cài Maven riêng vì dự án có `mvnw` / `mvnw.cmd`.

## Cài đặt và chạy ở máy local

### 1. Tải source

Clone repository hoặc giải nén source vào một thư mục. Mở terminal tại thư mục gốc — nơi có `pom.xml` và `mvnw.cmd`.

### 2. Tạo database MySQL

Đăng nhập MySQL bằng tài khoản có quyền tạo database và chạy:

```sql
CREATE DATABASE laptopshop
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
```

Ứng dụng đang cấu hình kết nối tới database `laptopshop` ở `localhost:3306`.

### 3. Cấu hình kết nối database

Mở file `src/main/resources/application.properties` và chỉnh thông tin cho khớp với MySQL trên máy:

```properties
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/laptopshop
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Thay `spring.datasource.username` và `spring.datasource.password` bằng tài khoản MySQL của bạn. Nếu MySQL chạy trên cổng khác, cập nhật URL tương ứng. `ddl-auto=update` cho phép Hibernate cập nhật cấu trúc bảng theo entity; nên sao lưu dữ liệu trước khi thay đổi schema.

**Lưu ý về dữ liệu ban đầu:** dự án có dùng các role tên `USER` và `ADMIN`. Đăng ký tài khoản mới cần có role `USER` trong bảng role trước đó; tài khoản quản trị cần được gán role phù hợp. Kiểm tra entity `Role`, `User` và `UserService` để xác nhận tên bảng/cột thực tế, sau đó thêm dữ liệu role theo schema được Hibernate tạo. Source không kèm script seed dữ liệu, nên database mới có thể cần bước khởi tạo này trước khi đăng ký/đăng nhập hoạt động đầy đủ.

### 4. Chạy ứng dụng

**Windows (PowerShell hoặc CMD):**

```bat
.\mvnw.cmd spring-boot:run
```

**macOS / Linux:**

```bash
chmod +x mvnw
./mvnw spring-boot:run
```

Lần chạy đầu tiên Maven sẽ tải dependencies nên có thể mất vài phút. Khi ứng dụng khởi động thành công, mở:

- Website: http://localhost:8080/
- Đăng nhập: http://localhost:8080/login
- Đăng ký: http://localhost:8080/register
- Quản lý sản phẩm: http://localhost:8080/admin/product (cần đăng nhập và quyền phù hợp)

Để dừng ứng dụng, nhấn `Ctrl + C` trong terminal.

### 5. Chạy bằng IntelliJ IDEA

1. Chọn **Open** và mở thư mục chứa `pom.xml`.
2. Đặt Project SDK là **Java 17** và chờ Maven tải dependencies.
3. Đảm bảo MySQL đã chạy, database đã tạo và `application.properties` đã được cập nhật.
4. Mở `src/main/java/vn/project/laptopshop/LaptopshopApplication.java`.
5. Chạy phương thức `main()` bằng nút Run.

## Cấu trúc thư mục

```text
src/main/java/vn/project/laptopshop/
├── config/          # Cấu hình Spring Security, MVC
├── controller/
│   ├── client/      # Trang chủ, đăng nhập/đăng ký, chi tiết sản phẩm
│   └── admin/       # Dashboard, sản phẩm, người dùng, đơn hàng
├── domain/          # Entity và DTO
├── repository/      # Truy vấn dữ liệu qua Spring Data JPA
├── service/         # Nghiệp vụ ứng dụng
└── LaptopshopApplication.java

src/main/resources/
└── application.properties

src/main/webapp/
└── WEB-INF/view/    # JSP views cho client và admin
```

## Một số lỗi thường gặp

- **`Communications link failure` / không kết nối được MySQL:** kiểm tra MySQL đã chạy chưa, cổng trong JDBC URL, username/password và database `laptopshop`.
- **`Unknown database 'laptopshop'`:** tạo database theo bước 2.
- **Lỗi thiếu role khi đăng ký:** đảm bảo bảng role có bản ghi tên `USER`; tạo/gán role quản trị nếu cần.
- **Port 8080 đang được sử dụng:** dừng ứng dụng đang chiếm cổng hoặc thêm `server.port=8081` vào `application.properties`, sau đó mở cổng mới.
- **Maven tải dependency thất bại:** kiểm tra kết nối mạng, proxy và thử chạy lại lệnh Maven.
- **Không đăng nhập được vào trang quản trị:** đăng ký tài khoản trước, kiểm tra role được gán trong database và quy tắc phân quyền trong `SecurityConfiguration` cùng controller.

## Ghi chú bảo mật

- Không commit mật khẩu database thật, file `.env`, thông tin tài khoản hay dữ liệu cá nhân lên GitHub.
- Đổi mật khẩu mẫu trong cấu hình trước khi chia sẻ hoặc triển khai.
- Chỉ cấp quyền quản trị cho tài khoản đáng tin cậy.

## Tác giả / liên hệ


