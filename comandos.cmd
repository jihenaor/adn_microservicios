@echo off
docker ps -a --format "table {{.ID}}\t{{.Image}}\t{{.Names}}\t{{.Ports}}" | Select-String -NotMatch "_kube-system"
