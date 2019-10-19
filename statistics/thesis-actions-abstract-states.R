generateMetrics <-function (path) {
	files <- list.files(path, pattern="actions_sequence_[[:alnum:]]+.csv", recursive = FALSE, full.names=TRUE)

	result <- read.table(files[1], sep=',', header=TRUE)
	
	for (currentFile in files) {
  		prevData <- read.table(currentFile, sep=',', header=TRUE)
  		result <- rbind(result, prevData) 
	}

	result <- table(result)
	
	print(result)
	
    png(filename="git/uni/thesis/statistics/actions-states.png", width = 1400, height = 800, res = NA, pointsize = 12, type = c("cairo", "cairo-png", "Xlib", "quartz"))
    barplot(result, main = 'SUT=notepad, Population=100, Generations=4, Actions=50, Sequences=1', xlab = 'Unique states', ylab = 'Actions that found x states after x actions', col="lightgreen")
   dev.off()

	mean(result)

	median(result)

	quantile(result, probs = c(.25, .5, .75, .95, .99))
	
	singleResults <- read.table(files[1], sep=',', header=TRUE)
	
	newResults = data.frame()
		
	for (currentFile in files) {
  		prevData <- read.table(currentFile, sep=',', header=TRUE)
  		i = 0;
        index = 0; 
        lastState = -1; 
        for (statesVisited in prevData[,2]) { 
	        i = i + 1; 
	        if (statesVisited != lastState) {
		        lastState = statesVisited;
		        index = i; 
		        res <- c(index, lastState)
	        }
         }
  		newResults <- rbind(newResults, res)
	}

	singleResult <- table(newResults)
	
	mean(singleResult)

	median(singleResult)

	quantile(singleResult, probs = c(.25, .5, .75, .95, .99))

	png(filename="git/uni/thesis/statistics/unique-states-actions.png", width = 1400, height = 800, res = NA, pointsize = 12, type = c("cairo", "cairo-png", "Xlib", "quartz"))
	barplot(singleResult, main = 'SUT=notepad, Population=100, Generations=4, Actions=50, Sequences=1', xlab = 'Unique states', ylab = 'Actions that found x states after x actions', col="lightgreen")
	dev.off()
	
	png(filename="git/uni/thesis/statistics/actions.png", width = 800, height = 800, res = NA, pointsize = 12, type = c("cairo", "cairo-png", "Xlib", "quartz"))
	hist(t(newResults[1]), main = 'SUT=notepad, Population=100, Generations=4, Actions=50, Sequences=1', xlab = '# of actions untill no new state is found', ylab = '# of strategies', col="lightgreen", labels = TRUE)
	dev.off()
	
	print(paste("Mean: ", mean(t(newResults[1])), sep = ""))

	print(paste("Median: ", median(t(newResults[1])), sep = ""))

	print(quantile(t(newResults[1]), probs = c(.25, .5, .75, .95, .99)))
}