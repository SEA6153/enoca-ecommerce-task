Enoca Ecommerce Task

Bu proje, Enoca backend challange taskı için hazırlanmış bir e-commerce uygulamasıdır. Kullanıcıların ürünleri sepete ekleyip, sepeti görüntüleyebileceği bir sistemdir.



Özellikler

-Müşteri yönetimi
-Ürün yönetimi
-Alışveriş sepeti (Ürün ekleme, çıkarma, görüntüleme ve sepeti temizleme)



Kullanılan Teknolojiler

Java 17
Spring Boot
H2 Database (in-memory)
Maven (proje yönetimi)
Lombok (daha temiz kod yazımı için)



Başlangıç

-Gereksinimler
-Java 17 veya üstü
-Maven




Adımlar

Projeyi GitHub'dan klonlayın:
git clone https://github.com/SEA6153/enoca-ecommerce-task.git
Projeyi çalıştırmak için terminalde şu komutu girin:
mvn spring-boot:run
Bu komut, uygulamanın 2020 portunda çalışmasını sağlayacaktır.

H2 veritabanı kullanılıyor. Uygulama çalışırken veritabanına şu URL üzerinden erişebilirsiniz:
http://localhost:2020/h2-console



API Endpoints

POST /cart/add: Sepete ürün ekler.
Parametreler: customerId, productId, quantity
DELETE /cart/remove: Sepetten ürün çıkarır.
Parametreler: customerId, productId
GET /cart: Sepeti görüntüler.
Parametre: customerId
DELETE /cart/empty: Sepeti boşaltır.
Parametre: customerId



H2 Veritabanı

H2 veritabanı in-memory (geçici) olarak çalıştığı için uygulama her başladığında veriler sıfırlanır. Veritabanına H2 konsolundan ulaşabilirsiniz.
H2 Konsolu URL: http://localhost:2020/h2-console
