#include "web.h"

Web::Web(QWidget *parent)
    : QWidget{parent}{
    window = new QWidget();
    window->setWindowTitle("Вартість біткоїна");

    label = new QLabel(window);
    label->setGeometry(50, 50, 200, 50);

    manager = new QNetworkAccessManager(this);

    const QUrl url("https://api.coindesk.com/v1/bpi/currentprice/BTC.json");

    QNetworkReply *reply = manager->get(QNetworkRequest(QUrl(url)));

    connect(reply, &QNetworkReply::finished, this, &Web::processResponse);
    //window->show();
}
void Web::processResponse() {
    reply = qobject_cast<QNetworkReply*>(sender());
    if (!reply) return;

    if (reply->error() == QNetworkReply::NoError) {
        const QJsonDocument document = QJsonDocument::fromJson(reply->readAll());
        const QJsonObject rootObject = document.object();
        if (rootObject.contains("bpi")) {
            const QJsonObject bpiObject = rootObject.value("bpi").toObject();
            if (bpiObject.contains("USD")) {
                const QJsonObject usdObject = bpiObject.value("USD").toObject();
                const QString rateString = usdObject.value("rate").toString();
                label->setText("Вартість біткоїна: " + rateString + " USD");
            }
        }
    } else {
        label->setText("Помилка при отриманні даних: " + reply->errorString());
    }

    reply->deleteLater();
    window->show();
}
