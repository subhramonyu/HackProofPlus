rm /Users/ramamoorthy.veeraraghavan/Q2Hackathon/*.html
rm *.html
#open -a /Applications/Docker.app
#sleep 60
#docker info
#docker system prune 
#docker pull owasp/zap2docker-stable
#docker pull owasp/zap2docker-weekly
#read site
#docker run -v $(pwd):/zap/wrk/:rw -t owasp/zap2docker-stable zap-baseline.py -t https://portal.qa.live-asset.com/ -g gen.conf -r testreport_baseline.html
/usr/local/bin/docker run -v $(pwd):/zap/wrk/:rw -t owasp/zap2docker-stable zap-api-scan.py -t https://portal.qa.live-asset.com/ -f openapi -g gen.conf -r testreport_api.html
#docker run -v $(pwd):/zap/wrk/:rw -t owasp/zap2docker-stable zap-full-scan.py -t https://portal.qa.live-asset.com/ -g gen.conf -r testreport_full.html 
#docker run -t owasp/zap2docker-stable zap-baseline.py -t $site
#docker run -t owasp/zap2docker-stable zap-api-scan.py -t $site -f openapi
#open testreport_baseline.html
open testreport_api.html
#open testreport_full.html

