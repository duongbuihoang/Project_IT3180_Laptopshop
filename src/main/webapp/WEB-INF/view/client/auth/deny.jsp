<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="vi">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Bạn không có quyền truy cập trang này - Laptopshop">
        <title>Không có quyền truy cập - Laptopshop</title>

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link
            href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
            rel="stylesheet">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
        <link href="/client/css/bootstrap.min.css" rel="stylesheet">
        <link href="/client/css/style.css" rel="stylesheet">

        <style>
            .access-denied-page {
                min-height: 100vh;
                display: flex;
                flex-direction: column;
                background: linear-gradient(135deg, #fff 0%, #fff8e8 100%);
            }

            .access-denied-content {
                flex: 1;
                display: flex;
                align-items: center;
                padding: 150px 0 80px;
            }

            .access-denied-card {
                position: relative;
                max-width: 760px;
                margin: 0 auto;
                padding: 52px 32px 48px;
                overflow: hidden;
                border: 1px solid rgba(129, 196, 8, .18);
                border-radius: 24px;
                background: rgba(255, 255, 255, .9);
                box-shadow: 0 18px 50px rgba(39, 60, 19, .1);
                text-align: center;
            }

            .access-denied-card::before,
            .access-denied-card::after {
                position: absolute;
                content: "";
                width: 180px;
                height: 180px;
                border-radius: 50%;
                background: rgba(255, 181, 36, .12);
            }

            .access-denied-card::before {
                top: -105px;
                left: -75px;
            }

            .access-denied-card::after {
                right: -90px;
                bottom: -120px;
                background: rgba(129, 196, 8, .1);
            }

            .access-denied-icon {
                position: relative;
                z-index: 1;
                display: inline-flex;
                width: 104px;
                height: 104px;
                align-items: center;
                justify-content: center;
                margin-bottom: 24px;
                border: 8px solid #fff4d8;
                border-radius: 50%;
                color: #fff;
                background: var(--bs-primary);
                box-shadow: 0 10px 22px rgba(255, 181, 36, .28);
                font-size: 42px;
            }

            .access-denied-code {
                margin-bottom: 4px;
                color: var(--bs-primary);
                font-family: Raleway, sans-serif;
                font-size: clamp(64px, 12vw, 112px);
                font-weight: 800;
                line-height: 1;
                letter-spacing: 4px;
            }

            .access-denied-title {
                color: #45595f;
                font-family: Raleway, sans-serif;
                font-size: clamp(24px, 4vw, 34px);
                font-weight: 800;
            }

            .access-denied-description {
                max-width: 510px;
                margin: 16px auto 30px;
                color: #6c757d;
                line-height: 1.8;
            }

            .access-denied-actions {
                position: relative;
                z-index: 1;
                display: flex;
                flex-wrap: wrap;
                justify-content: center;
                gap: 12px;
            }

            .access-denied-actions .btn {
                min-width: 176px;
            }

            @media (max-width: 767.98px) {
                .access-denied-content {
                    padding: 125px 15px 50px;
                }

                .access-denied-card {
                    padding: 40px 20px 36px;
                }
            }
        </style>
    </head>

    <body class="access-denied-page">
        <jsp:include page="../layout/header.jsp" />

        <main class="access-denied-content">
            <div class="container">
                <section class="access-denied-card" aria-labelledby="access-denied-title">
                    <div class="access-denied-icon" aria-hidden="true">
                        <i class="fas fa-lock"></i>
                    </div>
                    <div class="access-denied-code">403</div>
                    <h1 id="access-denied-title" class="access-denied-title">Bạn không có quyền truy cập</h1>
                    <p class="access-denied-description">
                        Xin lỗi, tài khoản của bạn chưa được cấp quyền xem trang này.
                        Hãy quay lại cửa hàng hoặc liên hệ quản trị viên nếu bạn cho rằng đây là một nhầm lẫn.
                    </p>
                    <div class="access-denied-actions">
                        <a href="/" class="btn btn-primary rounded-pill py-3 px-4">
                            <i class="fas fa-home me-2"></i>Về trang chủ
                        </a>

                    </div>
                </section>
            </div>
        </main>

        <jsp:include page="../layout/footer.jsp" />

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            crossorigin="anonymous"></script>
    </body>

    </html>