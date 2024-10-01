cd /opt
mkdir lab
cd lab
mkdir mysql redis nginx rabbitmq backend

#拿出MySQL的my.cnf
docker run --rm --entrypoint=cat mysql:5.7 /etc/my.cnf > ./mysql/my.cnf

#nginx
# 拿出 html 目录下的所有文件
docker run --rm -v ./nginx/html:/tmp nginx:1.27 sh -c "cp -r /usr/share/nginx/html/. /tmp/"
#创建access.log和error.log
touch ./nginx/logs/access.log
touch ./nginx/logs/error.log

