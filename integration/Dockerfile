FROM rabbitmq:3.6.12-management-alpine

COPY plugins/* /plugins/

RUN rabbitmq-plugins enable --offline \
    rabbitmq_management \
    autocluster \
    rabbitmq_aws \
    prometheus_process_collector \
    prometheus_rabbitmq_exporter \
    prometheus \
    prometheus_httpd