function filteredFile = filteringData(filepath, filename, PathToSaveFile)
close all;
filepath
filename
PathToSaveFile
Accelerometerjalllal = importfilecsvtomatrix(filepath);
subplot(511);
plot(Accelerometerjalllal);
subplot(512);
[B,A] = butter(2,0.05);
plot(filter(B,A,Accelerometerjalllal(:,1:3)));
hold on;
plot(Accelerometerjalllal(:,4));
subplot(513);
y= medfilt1(Accelerometerjalllal(:,1:3),23);
plot(y);
hold on;
plot(Accelerometerjalllal(:,4));
subplot(514);
plot(( Accelerometerjalllal(:,1:3)./(filter(B,A,Accelerometerjalllal(:,1:3)))));
subplot(515);
plot(( Accelerometerjalllal(:,1:3)./(y)));
SNR= (Accelerometerjalllal(:,1:3)./filter(B,A,Accelerometerjalllal(:,1:3)));
csvwrite([PathToSaveFile '\' filename],filter(B,A,Accelerometerjalllal(:,1:3)));
snr(Accelerometerjalllal(:,1), Accelerometerjalllal(:,1)-(filter(B,A,Accelerometerjalllal(:,1))))
snr(Accelerometerjalllal(:,1), Accelerometerjalllal(:,1)-medfilt1(Accelerometerjalllal(:,1),23))
snr(Accelerometerjalllal(:,2), Accelerometerjalllal(:,2)-(filter(B,A,Accelerometerjalllal(:,2))))
snr(Accelerometerjalllal(:,2), Accelerometerjalllal(:,2)-medfilt1(Accelerometerjalllal(:,2),23))
snr(Accelerometerjalllal(:,3), Accelerometerjalllal(:,3)-(filter(B,A,Accelerometerjalllal(:,3))))
snr(Accelerometerjalllal(:,3), Accelerometerjalllal(:,3)-medfilt1(Accelerometerjalllal(:,3),23))