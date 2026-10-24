<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="vi">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Xác nhận đặt hàng thành công tại Laptopshop">
        <title>Đặt hàng thành công - Laptopshop</title>

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link
            href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
            rel="stylesheet">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
        <link href="/client/css/bootstrap.min.css" rel="stylesheet">
        <link href="/client/css/style.css" rel="stylesheet">

        <style>
            .success-page {
                min-height: 620px;
                padding-top: 150px;
                padding-bottom: 80px;
                background: linear-gradient(180deg, #f8fbf4 0%, #ffffff 100%);
            }

            .success-card {
                max-width: 720px;
                border: 1px solid rgba(129, 196, 8, 0.2);
                border-radius: 18px;
                box-shadow: 0 12px 35px rgba(0, 0, 0, 0.08);
            }

            .success-icon {
                width: 92px;
                height: 92px;
                color: #ffffff;
                background: #81c408;
                border-radius: 50%;
                font-size: 46px;
                line-height: 92px;
                margin: 0 auto 24px;
                box-shadow: 0 8px 20px rgba(129, 196, 8, 0.3);
            }

            .next-step {
                background: #f8f9fa;
                border-radius: 12px;
            }

            .next-step i {
                width: 38px;
                color: #81c408;
                font-size: 20px;
            }
        </style>
    </head>

    <body>
        <jsp:include page="../layout/header.jsp" />

        <main class="success-page">
            <div class="container">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb mb-5">
                        <li class="breadcrumb-item"><a href="/">Trang chủ</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Đặt hàng thành công</li>
                    </ol>
                </nav>

                <section class="success-card bg-white mx-auto p-4 p-md-5 text-center">
                    <div class="success-icon">
                        <i class="fas fa-check"></i>
                    </div>

                    <h1 class="display-6 text-dark mb-3">Đặt hàng thành công!</h1>
                    <p class="lead mb-2">Cảm ơn bạn đã tin tưởng và mua sắm tại Laptopshop.</p>
                    <p class="text-muted mb-4">
                        Đơn hàng của bạn đã được tiếp nhận. Chúng tôi sẽ liên hệ với bạn để xác nhận
                        và giao hàng trong thời gian sớm nhất.
                    </p>

                    <div class="next-step text-start p-3 p-md-4 mb-4">
                        <h5 class="text-dark mb-3">Tiếp theo sẽ có gì?</h5>
                        <div class="d-flex align-items-start mb-3">
                            <i class="fas fa-phone-alt mt-1"></i>
                            <span>Nhân viên của Laptopshop sẽ gọi điện xác nhận thông tin đơn hàng.</span>
                        </div>
                        <div class="d-flex align-items-start">
                            <i class="fas fa-truck mt-1"></i>
                            <span>Đơn hàng sẽ được đóng gói và giao đến địa chỉ bạn đã cung cấp.</span>
                        </div>
                    </div>

                    <div class="d-flex flex-column flex-sm-row justify-content-center gap-3">
                        <a href="/" class="btn btn-primary rounded-pill py-3 px-4">
                            <i class="fas fa-home me-2"></i>Về trang chủ
                        </a>

                    </div>
                </section>
            </div>
        </main>

        <jsp:include page="../layout/footer.jsp" />

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>

    </html>