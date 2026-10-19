# Laptopshop

Website bán laptop được xây dựng bằng Java Spring Boot theo mô hình MVC. Dự án gồm giao diện cửa hàng cho khách hàng và khu vực quản trị dành cho người dùng có quyền `ADMIN`.

> Đây là dự án học tập. Không sử dụng thông tin đăng nhập mẫu hoặc cấu hình database phát triển khi triển khai production.

## Tính năng

### Khách hàng

- Xem danh sách sản phẩm tại trang chủ.
- Xem thông tin chi tiết sản phẩm.
- Đăng ký tài khoản.
- Đăng nhập và đăng xuất bằng Spring Security.
- Hiển thị trang thông báo khi tài khoản không có quyền truy cập.

### Quản trị viên

- Xem dashboard quản trị.
- Quản lý sản phẩm: danh sách, chi tiết, thêm, cập nhật, xóa và tải ảnh.
- Quản lý người dùng: danh sách, chi tiết, thêm, cập nhật và xóa.
- Xem danh sách đơn hàng.

## Công nghệ sử dụng

- Java 17
- Spring Boot 3.2.2
- Spring MVC
- Spring Data JPA / Hibernate
- Spring Security
- MySQL
- JSP / JSTL
- Bootstrap, HTML, CSS và JavaScript
- Maven Wrapper

## Yêu cầu môi trường

- JDK 17 trở lên
- MySQL 8.x hoặc phiên bản tương thích
- Git (nếu clone source từ repository)
- Không bắt buộc cài Maven riêng vì dự án đã có `mvnw` và `mvnw.cmd`

## Cài đặt

### 1. Clone source

```bash
git clone <repository-url>
cd 01-java-spring-laptopshop-starter-master
```

Hoặc mở trực tiếp thư mục dự án có chứa `pom.xml`.

### 2. Tạo database

Đăng nhập MySQL và tạo database:

```sql
CREATE DATABASE laptopshop
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;
```

Ứng dụng mặc định kết nối tới MySQL tại `localhost:3306/laptopshop`.

### 3. Cấu hình database

Mở [application.properties](src/main/resources/application.properties) và cập nhật thông tin kết nối:

```properties
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/laptopshop
spring.datasource.username=<mysql-username>
spring.datasource.password=<mysql-password>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```

Không commit mật khẩu thật vào Git. Có thể sử dụng biến môi trường hoặc cơ chế quản lý secrets của môi trường triển khai.

### 4. Khởi tạo role

Tài khoản đăng ký mới được gán role `USER`. Vì vậy, database cần có role này trước khi đăng ký tài khoản:

```sql
INSERT INTO roles (name, description)
VALUES ('USER', 'Khách hàng');

INSERT INTO roles (name, description)
VALUES ('ADMIN', 'Quản trị viên');
```

Nếu role đã tồn tại, không cần chạy lại các câu lệnh trên. Có thể kiểm tra bằng:

```sql
SELECT id, name, description
FROM roles;
```

Để cấp quyền quản trị cho một tài khoản:

```sql
UPDATE users
SET role_id = (SELECT id FROM roles WHERE name = 'ADMIN')
WHERE email = '<admin-email>';
```

Trong ứng dụng, role được chuyển thành authority theo quy tắc:

```text
USER  -> ROLE_USER
ADMIN -> ROLE_ADMIN
```

## Chạy ứng dụng

### Windows

Mở PowerShell hoặc Command Prompt tại thư mục gốc dự án:

```bat
.\mvnw.cmd spring-boot:run
```

### macOS / Linux

```bash
chmod +x mvnw
./mvnw spring-boot:run
```

Sau khi ứng dụng khởi động, truy cập:

- Trang chủ: http://localhost:8080/
- Đăng ký: http://localhost:8080/register
- Đăng nhập: http://localhost:8080/login
- Chi tiết sản phẩm: http://localhost:8080/product/{id}
- Dashboard admin: http://localhost:8080/admin
- Quản lý sản phẩm: http://localhost:8080/admin/product
- Quản lý người dùng: http://localhost:8080/admin/user
- Quản lý đơn hàng: http://localhost:8080/admin/order

Thay `{id}` bằng mã sản phẩm thực tế. Dừng ứng dụng bằng `Ctrl + C`.

## Phân quyền

Các route `/admin` và `/admin/**` yêu cầu role `ADMIN`:

```text
/admin
/admin/**
```

Nếu người dùng đã đăng nhập nhưng không có quyền, Spring Security chuyển hướng tới `/access-denied` và hiển thị view `client/auth/deny`.

Người dùng chưa đăng nhập khi truy cập route cần xác thực sẽ được chuyển tới trang đăng nhập.

## Chạy test

### Windows

```bat
.\mvnw.cmd test
```

### macOS / Linux

```bash
./mvnw test
```

Biên dịch mà không chạy test:

```bat
.\mvnw.cmd -DskipTests compile
```

## Cấu trúc thư mục

```text
src/
├── main/
│   ├── java/vn/project/laptopshop/
│   │   ├── config/          # Spring Security, MVC và xử lý đăng nhập
│   │   ├── controller/
│   │   │   ├── client/      # Trang cửa hàng, auth và chi tiết sản phẩm
│   │   │   └── admin/       # Dashboard, sản phẩm, user và order
│   │   ├── domain/          # Entity và DTO
│   │   ├── repository/      # Spring Data repositories
│   │   └── service/         # Logic nghiệp vụ
│   ├── resources/
│   │   └── application.properties
│   └── webapp/
│       ├── WEB-INF/view/    # JSP views client và admin
│       └── resources/       # CSS, JavaScript và hình ảnh
└── test/                    # Kiểm thử tự động
```

## Cấu hình bổ sung

Một số cấu hình hiện tại trong `application.properties`:

- Hibernate tự cập nhật schema với `spring.jpa.hibernate.ddl-auto=update`.
- Hiển thị SQL trong log với `spring.jpa.show-sql=true`.
- Giới hạn file upload: 50 MB.
- Giới hạn tổng request upload: 50 MB.
- Log Spring Security đang bật ở mức `DEBUG` để hỗ trợ phát triển.

Khi triển khai production, nên:

- Đổi `ddl-auto` sang quy trình migration có kiểm soát.
- Tắt log SQL và log security ở mức `DEBUG`.
- Dùng secrets/environment variables cho thông tin database.
- Giới hạn kích thước và định dạng file upload.
- Bật HTTPS và cấu hình domain phù hợp.

## Xử lý lỗi thường gặp

### Không kết nối được MySQL

Kiểm tra:

1. MySQL đã được khởi động.
2. Database `laptopshop` đã tồn tại.
3. Username, password và port trong `application.properties` chính xác.
4. Biến môi trường `MYSQL_HOST` không trỏ tới host sai.

### Lỗi `Unknown database 'laptopshop'`

Tạo database theo phần [Tạo database](#2-tạo-database).

### Đăng ký tài khoản bị lỗi do role

Đảm bảo bảng `roles` có role tên chính xác là `USER`. Tên role được phân biệt theo dữ liệu lưu trong database.

### USER truy cập khu vực admin

Đảm bảo:

- Ứng dụng đã được restart sau khi thay đổi cấu hình.
- Tài khoản đã logout và login lại để tạo phiên xác thực mới.
- Role của tài khoản trong database là `USER`, không phải `ADMIN`.
- Rule trong `SecurityConfiguration` vẫn bảo vệ `/admin` và `/admin/**`.

### Port 8080 đã được sử dụng

Thêm cấu hình sau vào `application.properties`:

```properties
server.port=8081
```

Sau đó truy cập http://localhost:8081/.

## Bảo mật

- Không commit password database, token, dữ liệu cá nhân hoặc file secrets.
- Không cấp role `ADMIN` cho tài khoản không tin cậy.
- Không sử dụng password mặc định trong môi trường thật.
- Nên thay đổi cấu hình log và upload trước khi triển khai production.

## Tác giả

Dự án phục vụ mục đích học tập và thực hành Java Spring Boot.
