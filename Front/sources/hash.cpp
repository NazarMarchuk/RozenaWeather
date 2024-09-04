#include "hash.h"

hash::hash(QString& base) {
    QByteArray byteArray = base.toUtf8();
    QByteArray hashArray = QCryptographicHash::hash(byteArray, QCryptographicHash::Sha256);

    QString hashString = QString(hashArray.toHex());

    base = hashString;
}
